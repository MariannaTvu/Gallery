package com.mariana.gallery.controllers;

import com.mariana.gallery.service.picture.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping("/")
public class SortingController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = "/sort_by_name")
    public String sortedByName(Model model, Principal principal) {
        if (principal != null) {
            String name = principal.getName();
            model.addAttribute("login", name);
        }
        model.addAttribute("pictures", pictureService.sortPicturesByName());
        return "/art";
    }

    @RequestMapping(value = "/sort_by_comments")
    public String sortedByComments(Model model, Principal principal) {
        if (principal != null) {
            String name = principal.getName();
            model.addAttribute("login", name);
        }
        model.addAttribute("pictures", pictureService.sortPicturesByComments());
        return "/art";
    }

    @RequestMapping(value = "/sort_by_date")
    public String sortedByDate(Model model, Principal principal) {
        if (principal != null) {
            String name = principal.getName();
            model.addAttribute("login", name);
        }
        model.addAttribute("pictures", pictureService.sortPicturesByDate());
        return "/art";
    }

    @RequestMapping(value = "/for_sale")
    public String forSale(Model model, Principal principal) {
        if (principal != null) {
            String name = principal.getName();
            model.addAttribute("login", name);
        }
        model.addAttribute("pictures", pictureService.forSalePictures());
        return "/art";
    }
}
