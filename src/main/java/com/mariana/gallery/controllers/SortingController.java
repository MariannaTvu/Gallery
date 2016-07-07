package com.mariana.gallery.controllers;

import com.mariana.gallery.service.picture.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SortingController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = "/sort_by_name")
    public String sortedByName(Model model) {
        model.addAttribute("pictures", pictureService.sortPicturesByName());
        model.addAttribute("picture_id", pictureService.sortPicturesByName());
        return "/art";
    }

    @RequestMapping(value = "/sort_by_comments")
    public String sortedByComments(Model model) {
        model.addAttribute("pictures", pictureService.sortPicturesByComments());
        model.addAttribute("picture_id", pictureService.sortPicturesByComments());
        return "/art";
    }

    @RequestMapping(value = "/sort_by_date")
    public String sortedByDate(Model model) {
        model.addAttribute("pictures", pictureService.sortPicturesByDate());
        model.addAttribute("picture_id", pictureService.sortPicturesByDate());
        return "/art";
    }
}
