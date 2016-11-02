package com.mariana.gallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class SortingController {

    @RequestMapping(value = "/sort_by_name")
    public String sortedByName(Model model) {
        model.addAttribute("sorting_type", "name");
        return "redirect:/art";
    }

    @RequestMapping(value = "/sort_by_comments")
    public String sortedByComments(Model model) {
        model.addAttribute("sorting_type", "comments");
        return "redirect:/art";
    }

    @RequestMapping(value = "/sort_by_date")
    public String sortedByDate(Model model) {
        model.addAttribute("sorting_type", "date");
        return "redirect:/art";
    }

    @RequestMapping(value = "/for_sale")
    public String forSale(Model model) {
        model.addAttribute("filter_type", "for_sale");
        return "redirect:/art";
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
        model.addAttribute("filter_type", "for_sale");
        return "redirect:/artist_gallery";
    }
}
