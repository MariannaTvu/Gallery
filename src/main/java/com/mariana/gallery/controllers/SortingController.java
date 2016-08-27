package com.mariana.gallery.controllers;

import com.mariana.gallery.service.gallery.GalleryService;
import com.mariana.gallery.service.picture.PictureService;
import com.mariana.gallery.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/")
public class SortingController {

    @Autowired
    private PictureService pictureService;

    @Autowired
    private UserService userService;

    @Autowired
    private GalleryService galleryService;

    @RequestMapping(value = "/sort_by_name")
    public String sortedByName(Model model, Principal principal) {
        if (principal != null) {
            String name = principal.getName();
            model.addAttribute("login", name);
        }
        model.addAttribute("pictures", pictureService.sortPicturesByName());
        return "/art";
    }
    @RequestMapping(value = "/author_sort_by_name/{gallery_id}" , method = RequestMethod.GET)
    public String authorsSortedByName(@PathVariable("gallery_id") long id,
            Model model, Principal principal) {
        if (principal != null) {
            String name = principal.getName();
            model.addAttribute("login", name);
        }
        model.addAttribute("gallery_id", id);
        model.addAttribute("pictures", pictureService.authorsPicturesByName(userService.findUserByGallery(galleryService.findUserGallery(id))));
        return "/artist_gallery/{gallery_id}";
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
