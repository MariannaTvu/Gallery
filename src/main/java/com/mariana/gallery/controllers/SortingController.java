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

    @RequestMapping(value = "/author_sort_by_name/{gallery_id}", method = RequestMethod.GET)
    public String authorsSortedByName(Model model, @PathVariable("gallery_id") long id) {
        model.addAttribute("gallery_id", id);
        model.addAttribute("sorting_type", "name");
        return "redirect:/artist_gallery";
    }

    @RequestMapping(value = "/author_sort_by_date/{gallery_id}", method = RequestMethod.GET)
    public String authorsSortedByDate(Model model, @PathVariable("gallery_id") long id) {
        model.addAttribute("gallery_id", id);
        model.addAttribute("sorting_type", "date");
        return "redirect:/artist_gallery";
    }

    @RequestMapping(value = "/author_sort_by_comments/{gallery_id}", method = RequestMethod.GET)
    public String authorsSortedByComments(Model model, @PathVariable("gallery_id") long id) {
        model.addAttribute("gallery_id", id);
        model.addAttribute("sorting_type", "comments");
        return "redirect:/artist_gallery";
    }

    @RequestMapping(value = "/author_for_sale/{gallery_id}", method = RequestMethod.GET)
    public String authorsForSale(Model model, @PathVariable("gallery_id") long id) {
         model.addAttribute("gallery_id", id);
        model.addAttribute("sorting_type", "for_sale");
        return "redirect:/artist_gallery";
    }
}
