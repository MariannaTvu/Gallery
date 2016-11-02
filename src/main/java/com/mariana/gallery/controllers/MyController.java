package com.mariana.gallery.controllers;

import com.mariana.gallery.persistence.picture.Picture;
import com.mariana.gallery.persistence.picture.PictureDAO;
import com.mariana.gallery.persistence.picture.PictureFilteringType;
import com.mariana.gallery.persistence.picture.PictureSortingType;
import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.persistence.user.UserDAO;
import com.mariana.gallery.persistence.user_gallery.UserGalleryDAO;
import com.mariana.gallery.persistence.user_gallery.UserGallery;
import com.mariana.gallery.service.gallery.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Transactional
@Controller
public class MyController {

    public static final int MAX_PICTURES_ON_BOARD = 26;

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private PictureDAO pictureDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserGalleryDAO userGalleryDAO;

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
    public String artGalleries(
            @RequestParam(value = "filter_type", defaultValue = "none") String filterTypeString,
            @RequestParam(value = "sorting_type", defaultValue = "none") String sortingTypeString,
            Model model) {
        PictureFilteringType filteringType = PictureFilteringType.byViewName(filterTypeString);
        PictureSortingType sortingType = PictureSortingType.byViewName(sortingTypeString);
        List<Picture> pictures = galleryService.getAllPictures(filteringType, sortingType);
        model.addAttribute("pictures", pictures);
        return "/art";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam("pattern") String namePattern, Model model) {
        List<Picture> result = pictureDAO.getByNamePattern(namePattern);
        model.addAttribute("pictures", result);
        return "/search_result";
    }

    /**
     * @param msg is a message with a link in JSP, that can redirect user to his cart, if the picture was successfully added
     * @return page with art by id
     */
    @RequestMapping(value = "/view_art", method = RequestMethod.GET)
    public String viewArtById(@RequestParam("picture_id") long id,
                              @ModelAttribute("msg") String msg,
                              Model model, Principal principal) {
        Picture pic = pictureDAO.getPictureById(id);
        if (pic == null) {
            return "redirect:/index";
        }
        model.addAttribute("picture", pic);
        model.addAttribute("same_user", isSameUser(pic, principal));
        model.addAttribute("msg", msg);
        return "/view_art";
    }

    private boolean isSameUser(Picture pic, Principal principal) {
        if (principal == null) {
            return false;
        }

        User user = userDAO.findUserByUsername(principal.getName());
        return pic.getAuthor().getId() == user.getId();
    }

    @RequestMapping("picture")
    public ResponseEntity<byte[]> downloadPhotoContent(@RequestParam("picture_id") long id) {
        Picture picture = pictureDAO.getPictureById(id);
        byte[] bytes = picture.getBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    @RequestMapping("/artist_gallery")
    public String artistGallery(@RequestParam("gallery_id") long galleryId,
                                @RequestParam(value = "filter_type", defaultValue = "none") String filterTypeString,
                                @RequestParam(value = "sorting_type", defaultValue = "none") String sortingTypeString,
                                Model model) {

        UserGallery gallery = userGalleryDAO.findById(galleryId);
        if (gallery == null) {
            return "redirect:/index";
        }

        PictureFilteringType filteringType = PictureFilteringType.byViewName(filterTypeString);
        PictureSortingType sortingType = PictureSortingType.byViewName(sortingTypeString);
        List<Picture> pictures = galleryService.getPicturesByGallery(gallery, filteringType, sortingType);

        model.addAttribute("pictures", pictures);
        model.addAttribute("author", gallery.getUser());
        return "/artist_gallery";
    }
}
