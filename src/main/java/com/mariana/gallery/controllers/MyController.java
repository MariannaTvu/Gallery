package com.mariana.gallery.controllers;

import com.mariana.gallery.controllers.exeptions.FileErrorException;
import com.mariana.gallery.persistence.picture.Picture;
import com.mariana.gallery.persistence.picture.PictureDAO;
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
import java.util.List;

@Controller
public class MyController {

    public static final int MAX_PICTURES_ON_BOARD = 26;

    @Autowired
    private GalleryService galleryService;
    @Autowired
    private UserService userService;
    @Autowired
    private PictureService pictureService;

    @Autowired
    private PictureDAO pictureDAO;

    @RequestMapping("/")
    public String onIndex() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        List<Picture> galleryPictures = pictureDAO.random(MAX_PICTURES_ON_BOARD);

        model.addAttribute("pictures", galleryPictures);

        return "index";
    }

    @RequestMapping("/art")
    public String artGalleries(Model model) {
        List<Picture> galleryPictures = pictureDAO.random(100);
        model.addAttribute("pictures", galleryPictures);
        return "/art";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam("pattern") String namePattern, Model model) {
        List<Picture> result = pictureDAO.getByNamePattern(namePattern);
        model.addAttribute("pictures", result);
        return "/search_result";
    }

    @RequestMapping(value = "/view_art", method = RequestMethod.GET)
    public String viewArtById(@RequestParam("picture_id") long id, Model model, Principal principal) {
        Picture pic = pictureDAO.getPictureById(id);
        if (pic == null) {
            return "redirect:/index";
        }

        model.addAttribute("picture", pic);
        model.addAttribute("same_user", isSameUser(pic, principal));

        return "/view_art";
    }

    private boolean isSameUser(Picture pic, Principal principal) {
        if (principal == null) {
            return false;
        }

        User user = userService.findUserByUsername(principal.getName());
        return pic.getAuthor().getId() == user.getId();
    }

    @RequestMapping("picture")
    public ResponseEntity<byte[]> downloadPhotoContent(@RequestParam("picture_id") long id) {
        Picture picture = pictureDAO.getPictureById(id);
        byte[] bytes = picture.getBytes();
        if (bytes == null) {
            throw new FileErrorException(); // TODO: handle this exception
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    @RequestMapping("/artist_gallery")
    public String artistGallery(@RequestParam("gallery_id") long galleryId,
                                @RequestParam(value = "sorting_type", defaultValue = "none") String sortingType,
                                Model model) {
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

            model.addAttribute("author", user);
            return "/artist_gallery";
        } catch (NoResultException e) {
            return "redirect:/index";
        }
    }
}
