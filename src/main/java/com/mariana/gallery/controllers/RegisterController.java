package com.mariana.gallery.controllers;

import com.mariana.gallery.persistence.user.UserRole;
import com.mariana.gallery.persistence.user_gallery.UserGallery;
import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.service.gallery.GalleryService;
import com.mariana.gallery.service.user.UserService;
import com.mariana.gallery.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

@Controller
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

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User userForm, BindingResult bindingResult) {
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

        UserGallery gal = galleryService.addUserGallery(new UserGallery(userForm.getLogin()));
        userForm.setUserGallery(gal);
        userService.save(userForm);

        authenticateUser(userForm);
        return "redirect:/user_details";
    }

    public void authenticateUser(User user) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getLogin());
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        user.getLogin(),
                        user.getPassword(),
                        userDetails.getAuthorities()));
    }
}