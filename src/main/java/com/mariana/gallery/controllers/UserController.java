package com.mariana.gallery.controllers;

import com.mariana.gallery.persistence.orders.Cart;
import com.mariana.gallery.persistence.picture.Picture;
import com.mariana.gallery.persistence.picture.PictureComment;
import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.persistence.user_gallery.UserGallery;
import com.mariana.gallery.service.gallery.GalleryService;
import com.mariana.gallery.service.orders.CartService;
import com.mariana.gallery.service.picture.PictureService;
import com.mariana.gallery.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping()
public class UserController {
    @Autowired
    private GalleryService galleryService;
    @Autowired
    private UserService userService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/add_comment", method = RequestMethod.GET)
    public String addComment(@ModelAttribute("picture_id") long id, @RequestParam("comment") String comment,
                             Model model, Principal principal) {
        if (principal != null) {
            if (!comment.isEmpty()) {
                model.addAttribute("picture_id", id);
                Picture pic = pictureService.getPictureById(id);
                PictureComment text = new PictureComment(comment);
                text.setPictures(pic);
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                text.setDate(dateFormat.format(date));
                text.setAuthor(principal.getName());
                pictureService.update(pic);
                pictureService.updateComment(text);
            }
        }
        return "redirect:/view_art";
    }

    @RequestMapping("/upload_art")
    public String uploadArt(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.findUserByUsername(principal.getName());
            model.addAttribute("user", user);
        }
        return "/upload_art";
    }

    @RequestMapping("/user_details")
    public String seeUserDetails(Model model, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        List<Cart> orderHistory = cartService.getUsersOrderHistory(user);
        if (orderHistory.size() == 0) {
            model.addAttribute("msg", "You have no orders yet");
        }
        model.addAttribute("user", user);
        model.addAttribute("orders", orderHistory);
        return "/user_details";
    }

    @RequestMapping("/user_pictures")
    public String userPictures(Model model, Principal principal) {
        if (principal != null) {
            UserGallery gallery = galleryService.findUserGallery(userService.findUserByUsername(principal.getName()).getId());
            model.addAttribute("picture_id", pictureService.getPicturesByGallery(gallery));
            model.addAttribute("pictures", pictureService.getPicturesByGallery(gallery));
            model.addAttribute("author", userService.findUserByUsername(principal.getName()));
        }
        return "/edit_gallery";
    }

    @RequestMapping(value = "/delete_picture/{picture_id}", method = RequestMethod.GET)
    public String deletePicById(@PathVariable("picture_id") long id, Model model) {
        model.addAttribute("picture_id", id);
        return "redirect:/delete_picture";
    }

    @RequestMapping("/delete_picture")
    public String editGallery(@ModelAttribute("picture_id") long id, Model model) {
        try {
            pictureService.deletePictureById(id);
            return "redirect:/user_details";
        } catch (EntityNotFoundException e) {
        }
        return "/user_details";
    }


    @RequestMapping(value = "/user_picture/{user_id}", method = RequestMethod.GET)
    public String findToDeleteUserById(@PathVariable("user_id") long id, Model model) {
        model.addAttribute("user_id", id);
        return "redirect:/delete_user";
    }

    //add picture
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String pictureAdd(@RequestParam("picture_name") String pictureName,
                             @RequestParam("picture_description") String pictureDescription,
                             @RequestParam("picture_price") String rawPicturePrice,
                             @RequestParam("file") MultipartFile file,
                             Principal principal, Model model) throws IOException {
        if (principal != null) {
            User user = userService.findUserByUsername(principal.getName());
            model.addAttribute("user", user);

            try {
                if (!file.isEmpty()) {
                    Picture picture = new Picture(file.getBytes());
                    if (!pictureName.isEmpty()) {
                        picture.setName(pictureName);

                    } else {
                        String msg = "Please, name the picture";
                        model.addAttribute("error", msg);
                        return "/upload_art";
                    }
                    if (!pictureDescription.isEmpty()) {
                        picture.setDescription(pictureDescription);
                    }
                    picture.setAuthor(user);
                    picture.setAvailable(true);
                    picture.setUserGallery(user.getUserGallery());
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    picture.setDateAdded(dateFormat.format(date));
                    if (rawPicturePrice != null) {
                        try {
                            double rawDoublePicturePrice = Double.parseDouble(rawPicturePrice);
                            Double picturePrice = rawDoublePicturePrice * 100;
                            picture.setPrice(picturePrice.intValue());
                        }catch (Exception e){
                            String msg = "Please, set the price using numbers";
                            model.addAttribute("error", msg);
                            return "/upload_art";
                        }
                    } else {
                        picture.setPrice(0);
                    }
                    pictureService.addPicture(picture);
                    return "redirect:/art";
                } else {
                    String msg = "Please, upload file";
                    model.addAttribute("error", msg);
                    return "/upload_art";
                }
            } catch (PersistenceException e) {
                String msg = "File, that you are trying to upload, is too large";
                model.addAttribute("error", msg);
                return "/upload_art";
            }
        }
        return "/upload_art";
    }

    @RequestMapping(value = "/add_bio", method = RequestMethod.POST)
    public String bioAdd(@RequestParam String bio, Model model, Principal principal) {
        if (principal != null) {
            User user = userService.findUserByUsername(principal.getName());
            userService.addUserBio(user, bio);
            model.addAttribute("gallery_id", user.getId());
        }
        return "redirect:/artist_gallery/{gallery_id}";
    }

    @RequestMapping("/edit_gallery")
    public String editGallery(@ModelAttribute("gallery_id") long galleryId, Model model, Principal principal) {
        UserGallery gallery = galleryService.findUserGallery(galleryId);
        User user = userService.findUserByGallery(gallery);
        List<Picture> galleryPictures = pictureService.getPicturesByGallery(gallery);
        List<Long> response = new ArrayList<>();
        for (Picture picture : galleryPictures) {
            long id = picture.getId();
            response.add(id);
        }
        if (principal != null) {
            String name = principal.getName();
            model.addAttribute("login", name);
        }
        model.addAttribute("picture_id", response);
        model.addAttribute("pictures", galleryPictures);
        model.addAttribute("author", user);
        return "/edit_gallery";
    }

    @RequestMapping("/admin")
    public String adminPage() {
        return "/admin";
    }

    @RequestMapping("/admin/delete_picture")
    public String adminDeletePicture(@ModelAttribute("picture_id") long pictureId, Model model) {
        model.addAttribute("picture_id", pictureId);
        return "redirect:/delete_picture";
    }

    @RequestMapping(value = "/admin/set_balance", method = RequestMethod.GET)
    public String setBalance(@RequestParam("new_balance") int newBalance, @RequestParam("user_id") long userId) {
        try {
            User user = userService.findUserById(userId);
            user.setBalance(newBalance);
            userService.save(user);
        } catch (NoResultException ex) {
        }
        return "redirect:/admin";
    }
}
