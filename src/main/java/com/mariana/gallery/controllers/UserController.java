package com.mariana.gallery.controllers;

import com.mariana.gallery.service.gallery.GalleryService;
import com.mariana.gallery.persistence.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.NoResultException;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET)
    public String showUserInfo(Model model, Principal principal) {
        String email = principal.getName();
        User user = galleryService.findUserByEmail(email);


        model.addAttribute("existingAccount", user);
        return "user";
    }



    private void updateUser(User changedUser, Model model, Principal principal) {
        User existingUser = null;
        try {
            existingUser = galleryService.findUserByEmail(principal.getName());
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        String existingPw = existingUser.getPassword();

        if (!changedUser.getPassword().equals(existingPw)) {
            String newEncodedPw = passwordEncoder.encodePassword(changedUser.getPassword(), null);
            changedUser.setPassword(newEncodedPw);
        }

        changedUser.setId(existingUser.getId());

        changedUser.setRole(existingUser.getRole());
        galleryService.saveUser(changedUser);

        model.addAttribute("existingUser", changedUser);
    }

    private void returnError(User changedUser, Model model) {
        changedUser.setPassword("");
        model.addAttribute("error", new Object());
        model.addAttribute("existingUser", changedUser);
    }

    private boolean isChangedEmailOccupied(User changedUser, Principal principal) {
        String changedEmail = changedUser.getEmail();
        String existingEmail = principal.getName();
        if (changedEmail.equals(existingEmail))
            return false;
        User userByEmail = null;
        try {
            userByEmail = galleryService.findUserByEmail(changedEmail);
        } catch (NoResultException e) {
            return false;
        }
        return true;
    }

}