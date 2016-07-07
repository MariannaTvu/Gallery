package com.mariana.gallery.controllers;

import com.mariana.gallery.controllers.exeptions.FileErrorException;
import com.mariana.gallery.persistence.picture.Picture;
import com.mariana.gallery.persistence.picture.PictureComment;
import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.service.gallery.GalleryService;
import com.mariana.gallery.persistence.user_gallery.UserGallery;
import com.mariana.gallery.service.picture.PictureService;
import com.mariana.gallery.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.persistence.NoResultException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class MyController {
    @Autowired
    private GalleryService galleryService;
    @Autowired
    private UserService userService;
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/")
    public String onIndex(Model model) {
        return "redirect:/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, Principal principal) {
        List<Picture> galleryPictures = pictureService.random();
        if (galleryPictures.size() > 26) {
            galleryPictures = galleryPictures.subList(0, 25);
        }
        List<Long> response = new ArrayList<>();
        for (Picture picture : galleryPictures) {
            long id = picture.getId();
            response.add(id);
        }
        model.addAttribute("pictures", galleryPictures);
        model.addAttribute("picture_id", response);
        if (principal != null) {
            String name = principal.getName(); //get logged in username
            model.addAttribute("login", name);
        }
        return "index";
    }


    @RequestMapping("/art")
    public String artGalleries(Model model, Principal principal) {
        List<Picture> galleryPictures = pictureService.random();
        List<Long> response = new ArrayList<>();
        for (Picture picture : galleryPictures) {
            long id = picture.getId();
            response.add(id);
        }
        model.addAttribute("pictures", galleryPictures);
        model.addAttribute("picture_id", response);

        if (principal != null) {
            String name = principal.getName(); //get logged in username
            model.addAttribute("login", name);
        }
        return "/art";
    }

    @RequestMapping("/reg")
    public String registration() {
        return "redirect:/registration";
    }

    @RequestMapping("/profile")
    public String userDetails() {
        return "redirect:/user_details";
    }

    @RequestMapping("/gal")
    public String artistGal(Model model) {
        return "redirect:/artist_gallery";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String pattern, Model model) {
        model.addAttribute("pictures", pictureService.searchPictures(pattern));
        return "/search_result";
    }

    @RequestMapping(value = "/view_art/{picture_id}", method = RequestMethod.GET)
    public String viewArtById(@PathVariable("picture_id") long id, Model model) {
        model.addAttribute("picture_id", id);
        return "redirect:/view_art";
    }

    @RequestMapping(value = "/view_art", method = RequestMethod.GET)
    public String viewArt(@ModelAttribute("picture_id") long id, Model model) {
        try {
            Picture pic = pictureService.getPictureById(id);
            List<PictureComment> comments = pic.getPictureComments();
            model.addAttribute("author", pic.getAuthor().getLogin());
            model.addAttribute("picture_id", id);
            model.addAttribute("picture", pic);
            model.addAttribute("comments", comments);
            return "/view_art";
        } catch (NullPointerException e) {
        }
        return "redirect:/index";
    }

    @RequestMapping("picture/{picture_id}")
    public ResponseEntity<byte[]> onPhoto(@PathVariable("picture_id") long id, Model model) {
        model.addAttribute("picture_id", id);
        return pictureById(id);
    }

    @RequestMapping(value = "/artist_gallery/{gallery_id}", method = RequestMethod.GET)
    public String viewGalleryById(@PathVariable("gallery_id") long galleryId, Model model) {
        try {
            model.addAttribute("gallery_id", galleryId);
            return "redirect:/artist_gallery";
        } catch (NoResultException e) {
        }
        return "redirect:/index";
    }

    @RequestMapping("/artist_gallery")
    public String artistGallery(@ModelAttribute("gallery_id") long galleryId, Model model, Principal principal) {
        try {
            UserGallery gallery = galleryService.findUserGallery(galleryId);
            User user = userService.findUserByGallery(gallery);
            List<Picture> galleryPictures = pictureService.getPicturesByGallery(gallery);
            List<Long> response = new ArrayList<>();
            for (Picture picture : galleryPictures) {
                long id = picture.getId();
                response.add(id);
            }
            if (principal != null) {
                String name = principal.getName(); //get logged in username
                model.addAttribute("login", name);
            }
            model.addAttribute("picture_id", response);
            model.addAttribute("pictures", galleryPictures);
            model.addAttribute("author", user);
            return "/artist_gallery";
        } catch (NoResultException e) {
        }
        return "redirect:/index";
    }

    private ResponseEntity<byte[]> pictureById(long id) {
        byte[] bytes = pictureService.getPictureBytesById(id);
        if (bytes == null) {
            throw new FileErrorException();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }

}

