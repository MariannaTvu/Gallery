package com.mariana.gallery.controllers;


import com.mariana.gallery.persistence.orders.Cart;
import com.mariana.gallery.persistence.picture.Picture;
import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.service.gallery.GalleryService;
import com.mariana.gallery.service.orders.CartService;
import com.mariana.gallery.service.picture.PictureService;
import com.mariana.gallery.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private GalleryService galleryService;
    @Autowired
    private UserService userService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/add_to_cart", method = RequestMethod.POST)
    public String addToCart(@ModelAttribute("picture_id") long pictureId, Model model, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        Picture picture = pictureService.getPictureById(pictureId);
        if (pictureService.getPictureAuthor(picture).getId()!=(user.getId())) {
            Date date = new Date();
            Cart cart = new Cart(date, picture, user);
            cart.setSumCost(picture.getPrice());
            cartService.createOrder(cart);
        }


        return "redirect:/view_art";
    }

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public String buyArt(@RequestParam("selectedItems") long[] orderIds,
                         Model model, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        for (long id : orderIds) {
            Cart cart = cartService.getOrderById(id);
            Picture picture = cart.getPicture();
            int newBalance = user.getBalance() - picture.getPrice();
            user.setBalance(newBalance);
            Date date = new Date();
            cartService.setPurchaseDate(cart, date);
            cartService.confirmOrder(id);
            pictureService.update(picture);
            userService.save(user);
        }
        return "redirect:/shop";
    }

    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public String shop(Model model, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        List<Cart> userCarts = cartService.getUserCart(user);


        String name = principal.getName();
        model.addAttribute("login", name);
        model.addAttribute("orders", userCarts);
        return "/shop";
    }


    @RequestMapping(value = "/remove/{idToRemove}", method = RequestMethod.GET)
    public String deletePicById(@PathVariable("idToRemove") long id, Model model) {
        model.addAttribute("idToRemove", id);
        return "redirect:/remove";
    }

    @RequestMapping("/remove")
    public String editGallery(@ModelAttribute("idToRemove") long idToRemove, Model model) {
        try {
            cartService.deleteOrderById(idToRemove);
            return "redirect:/shop";
        } catch (EntityNotFoundException e) {
        }
        return "redirect:/shop";
    }


}
