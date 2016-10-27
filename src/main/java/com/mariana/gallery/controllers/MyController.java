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
public class MyController {
    @Autowired
    private GalleryService galleryService;
    @Autowired
    private UserService userService;
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/")
    public String onIndex() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, Principal principal) {
        List<Picture> galleryPictures = pictureService.random();
        if (galleryPictures.size() > 10) {
            galleryPictures = galleryPictures.subList(0, 9);
        }
        List<Picture> smallGalleryPictures = galleryPictures.subList(0,5);
        List<Picture> smallGalleryPicturesFix = galleryPictures.subList(6,7);
        List<Picture> bigGalleryPictures = galleryPictures.subList(8,9);
//        List<Long> response = new ArrayList<>();
//        for (Picture picture : galleryPictures) {
//            long id = picture.getId();
//            response.add(id);
//        }
        model.addAttribute("small_pictures", smallGalleryPictures);
        model.addAttribute("small_pictures_fix", smallGalleryPicturesFix);
        model.addAttribute("pictures", galleryPictures);
     //   model.addAttribute("picture_id", response);
        if (principal != null) {
            String name = principal.getName(); //get logged in username
            model.addAttribute("login", name);
        }
        return "index";
    }

    @RequestMapping("/reg")
    public String registration() {
        return "redirect:/registration";
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
            String name = principal.getName();
            model.addAttribute("login", name);
        }
        return "/art";
    }

    @RequestMapping("/profile")
    public String userDetails() {
        return "redirect:/user_details";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam String pattern, Model model, Principal principal) {

        if (principal != null) {
            String name = principal.getName();
            model.addAttribute("login", name);
        }
        List<Picture> result = pictureService.searchPictures(pattern);
        if (result.isEmpty()) {
            String msg = "No matching results";
            model.addAttribute("msg", msg);
        }
        model.addAttribute("pictures", pictureService.searchPictures(pattern));

        return "/search_result";
    }

    @RequestMapping(value = "/view_art/{picture_id}", method = RequestMethod.GET)
    public String viewArtById(@PathVariable("picture_id") long id, Model model, Principal principal) {
        model.addAttribute("picture_id", id);
        if (principal != null) {
            String name = principal.getName();
            model.addAttribute("login", name);
        }
        return "redirect:/view_art";
    }

    @RequestMapping(value = "/view_art", method = RequestMethod.GET)
    public String viewArt(@ModelAttribute("picture_id") long id, @ModelAttribute("login") String name,
                          @ModelAttribute("msg") String msg, Model model, Principal principal) {
        try {
            Picture pic = pictureService.getPictureById(id);
            List<PictureComment> comments = pic.getPictureComments();
            model.addAttribute("author", pic.getAuthor().getLogin());
            model.addAttribute("picture_id", id);
            model.addAttribute("picture", pic);
            model.addAttribute("comments", comments);
            model.addAttribute("msg", msg);
            if (principal != null) {
                User user = userService.findUserByUsername(principal.getName());
                if (pictureService.getPictureAuthor(pic).getId() != (user.getId())) {
                    model.addAttribute("same_user", false);
                }
            }
            if (principal == null) {
                model.addAttribute("same_user", false);
            }
            return "/view_art";
        } catch (NullPointerException e) {
            return "redirect:/index";
        }
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
            model.addAttribute("sorting_type", "none");
            return "redirect:/artist_gallery";
        } catch (NoResultException e) {
        }
        return "redirect:/index";
    }

    @RequestMapping("/artist_gallery")
    public String artistGallery(@RequestParam("gallery_id") long galleryId,
                                @RequestParam("sorting_type") String sortingType,
                                Model model, Principal principal) {
        try {
            UserGallery gallery = galleryService.findUserGallery(galleryId);
            User user = userService.findUserByGallery(gallery);
            if (sortingType.equals("none")) {
                List<Picture> galleryPictures = pictureService.getPicturesByGallery(gallery);
                model.addAttribute("pictures", galleryPictures);
            }
            if (sortingType.equals("date")) {
                model.addAttribute("pictures", pictureService.authorsPicturesByDate(user));
            }
            if (sortingType.equals("name")) {
                model.addAttribute("pictures", pictureService.authorsPicturesByName(user));
            }
            if (sortingType.equals("comments")) {
                model.addAttribute("pictures", pictureService.authorsPicturesByComments(user));
            }
            if (sortingType.equals("for_sale")) {
                model.addAttribute("pictures", pictureService.authorsPicturesForSale(user));
            }

            if (principal != null) {
                String name = principal.getName();
                model.addAttribute("login", name);
            }
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



