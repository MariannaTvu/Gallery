package com.mariana.gallery.controllers;

import com.mariana.gallery.controllers.exeptions.FileErrorException;
import com.mariana.gallery.persistence.picture.Picture;
import com.mariana.gallery.persistence.picture.PictureComment;
import com.mariana.gallery.service.gallery.GalleryService;
import com.mariana.gallery.persistence.user_gallery.UserGallery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class MyController {

    @Autowired
    private GalleryService galleryService;

    @RequestMapping("/")
    public String onIndex(Model model) {

        return "redirect:/index";
    }

    @RequestMapping("/auth")
    public String authorize(Model model) {

        return "redirect:/authorize";
    }


    @RequestMapping("/index")
    public String index(Model model) {
        UserGallery gal = galleryService.listUserGallerys().get(0);
        List<Picture> galleryPictures = galleryService.getPicturesByGallery(gal);
        List<Long> response = new ArrayList<>();
        for (Picture picture : galleryPictures) {
            long id = picture.getId();
            response.add(id);
        }
       model.addAttribute("pictures", galleryService.listPictures());
        model.addAttribute("picture_id", galleryService.listPictures());
        // model.addAttribute("picture_id", response);

        return "index";
    }


    @RequestMapping("/reg")
    public String registration() {
        return "redirect:/registration";
    }

    @RequestMapping("/gal")
    public String artistGal(Model model) {
        return "redirect:/artist_gallery";
    }

    @RequestMapping(value ="/sort_by_name")
    public String sortedByName(Model model) {
      //  galleryService.sortPicturesByName(null);
        model.addAttribute("pictures", galleryService.sortPicturesByName());
        model.addAttribute("picture_id", galleryService.sortPicturesByName());
        return "index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String pattern, Model model) {
        model.addAttribute("pictures", galleryService.searchPictures(pattern));
        return "index";
    }

    @RequestMapping(value = "/view_art/{picture_id}", method = RequestMethod.GET)
    public String viewArtById(@PathVariable("picture_id") long id, Model model) {
        model.addAttribute("picture_id", id);
        return "redirect:/view_art";
    }


    @RequestMapping(value = "/view_art", method = RequestMethod.GET)
    public String viewArt(@ModelAttribute("picture_id") long id, Model model) {
        Picture pic = galleryService.getPictureById(id);
        List<PictureComment> comments = pic.getPictureComments();
        model.addAttribute("picture_id", id);
        model.addAttribute("picture", pic);
        model.addAttribute("comments", comments);
        return "/view_art";
    }


    @RequestMapping("picture/{picture_id}")
    public ResponseEntity<byte[]> onPhoto(@PathVariable("picture_id") long id, Model model) {
        model.addAttribute("picture_id", id);
        return pictureById(id);
    }


    @RequestMapping("/artist_gallery")
    public String artistGallery(Model model) {
        UserGallery gal = galleryService.listUserGallerys().get(0);
        List<Picture> galleryPictures = galleryService.getPicturesByGallery(gal);
        List<Long> response = new ArrayList<>();

        for (Picture picture : galleryPictures) {
            long id = picture.getId();
            response.add(id);
        }
        model.addAttribute("picture_id", response);
        model.addAttribute("pictures", galleryPictures);
        return "/artist_gallery";
    }

    @RequestMapping("/upload_art")
    public String uploadArt(Model model) {
        return "/upload_art";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String pictureAdd(@RequestParam("picture_name") String pictureName, @RequestParam("file") MultipartFile file,
                             Model model) throws IOException {

        Picture picture = new Picture(file.getBytes());
        picture.setName(pictureName);
        galleryService.addPicture(picture);
        return "artist_gallery";
    }

    @RequestMapping(value = "/gallery/add", method = RequestMethod.POST)
    public String groupAdd(@RequestParam String name, Model model) {
        galleryService.addUserGallery(new UserGallery(name));
        model.addAttribute("pictures", galleryService.listUserGallerys());
        return "index";
    }

    @RequestMapping(value = "/set/group", method = RequestMethod.POST)
    public String setAllGroup1(Model model) {
        UserGallery gal1 = galleryService.listUserGallerys().get(0);
        List<Picture> pic1 = galleryService.listPictures();
        for (Picture pic2 : pic1) {
            pic2.setUserGallery(gal1);
            pic2 = galleryService.update(pic2);
        }

        return "/";
    }

    private ResponseEntity<byte[]> pictureById(long id) {
        byte[] bytes = galleryService.getPictureBytesById(id);
        if (bytes == null)
            throw new FileErrorException();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }


    @RequestMapping(value = "/add_comment", method = RequestMethod.GET)
    public String addComment(@ModelAttribute("picture_id") long id, @RequestParam("comment") String comment,
                             Model model) {
        model.addAttribute("picture_id", id);
        Picture pic = galleryService.getPictureById(id);
        PictureComment text = new PictureComment(comment);
        galleryService.addComment(text);
        text.setPicture(pic);
        galleryService.updateComment(text);
        galleryService.update(pic);
        return "redirect:/view_art";
    }




}

