package com.mariana.gallery.controllers;

import com.mariana.gallery.persistence.user.UserRole;
import com.mariana.gallery.persistence.user_gallery.UserGallery;
import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.persistence.NoResultException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RegisterController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User userForm, Model model) {
        try {userService.findUserByUsername(userForm.getLogin());
        } catch (NoResultException exc)  {
        String pass = passwordEncoder.encode(userForm.getPassword());
        userForm.setPassword(pass);
        userForm.setRole(UserRole.USER);
        UserGallery gal = new UserGallery(userForm.getLogin());
            userService.saveUser(userForm, gal);
        authenticateUser(userForm);
            return "redirect:/user_details";
        }
        String msg = "Sorry, this username us taken. Please, try another one";
        model.addAttribute("error", msg);
        return "/registration";
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