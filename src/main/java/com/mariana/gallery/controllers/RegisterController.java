package com.mariana.gallery.controllers;

import com.mariana.gallery.persistence.user.UserRole;
import com.mariana.gallery.persistence.user_gallery.UserGallery;
import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.service.gallery.GalleryService;
import com.mariana.gallery.service.user.UserService;
import com.mariana.gallery.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@Controller
@RequestMapping("/")
public class RegisterController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private GalleryService galleryService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User userForm, BindingResult bindingResult
    ) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/registration";
        }
        String pass = passwordEncoder.encode(userForm.getPassword());
        String confPass = passwordEncoder.encode(userForm.getPasswordConfirm());
        userForm.setPassword(pass);
        userForm.setPasswordConfirm(confPass);
        userForm.setRole(UserRole.USER);
        userForm.setBalance(1500000);

        UserGallery gal = new UserGallery(userForm.getLogin());
        galleryService.addUserGallery(gal);
        userForm.setUserGallery(gal);
        userService.save(userForm);

        authenticateUser(userForm);

        return "redirect:/user_details";
    }

    public void authenticateUser(User user) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getLogin());
        SecurityContextHolder.getContext().setAuthentication(
                new Authentication() {
                    @Override
                    public Collection<? extends GrantedAuthority> getAuthorities() {
                        return userDetails.getAuthorities();
                    }

                    @Override
                    public Object getCredentials() {
                        return userDetails.isCredentialsNonExpired();
                    }

                    @Override
                    public Object getDetails() {
                        return userDetails.getUsername();
                    }

                    @Override
                    public Object getPrincipal() {
                        return userDetails;
                    }

                    @Override
                    public boolean isAuthenticated() {
                        return userDetails.isEnabled();
                    }

                    @Override
                    public void setAuthenticated(boolean b) throws IllegalArgumentException {
                    }

                    @Override
                    public String getName() {
                        return userDetails.getUsername();
                    }
                });
    }
}