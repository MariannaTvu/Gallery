package com.mariana.gallery.controllers;

import com.mariana.gallery.persistence.user.UserRole;
import com.mariana.gallery.service.gallery.GalleryService;
import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.persistence.user.UserDAO;
import com.mariana.gallery.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
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
    private SecurityService securityService;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
       User user = new User();
        user.setRole(UserRole.USER);
        model.addAttribute("user", user);

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User userForm) {
        userDAO.saveUser(userForm);
        securityService.autologin(userForm.getLogin(), userForm.getPassword());
        //  securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/index";
    }
}