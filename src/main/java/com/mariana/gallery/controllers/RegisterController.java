package com.mariana.gallery.controllers;

import com.mariana.gallery.persistence.user.UserRole;
import com.mariana.gallery.service.gallery.GalleryService;
import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.persistence.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RegisterController {

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User userForm) {
        String pass = passwordEncoder.encode(userForm.getPassword());
        userForm.setPassword(pass);
        userForm.setRole(UserRole.USER);

        userDAO.saveUser(userForm);

        //  securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/index";
    }
}