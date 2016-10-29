package com.mariana.gallery.controllers;


import com.mariana.gallery.persistence.orders.Cart;
import com.mariana.gallery.persistence.picture.Picture;
import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.service.orders.CartService;
import com.mariana.gallery.service.picture.PictureService;
import com.mariana.gallery.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Controller
public class CartController {
    @Autowired
    private UserService userService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/add_to_cart", method = RequestMethod.GET)
    public String addToCart(@ModelAttribute("picture_id") long pictureId, Principal principal, Model model) {
        if (principal != null) {
            User user = userService.findUserByUsername(principal.getName());
            Picture picture = pictureService.getPictureById(pictureId);
            if (picture.getAuthor().getId() != (user.getId())) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Kiev"));
                Date date = new Date();
                Cart cart = new Cart(dateFormat.format(date), picture, user);
                cart.setSumCost(picture.getPrice());
                cartService.createOrder(cart);
                String msg = "Order added. You now can see it in your cart.";
                model.addAttribute("msg", msg);
            }
        }
        return "redirect:/view_art";
    }

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public String buyArt(@RequestParam("selectedItems") long[] orderIds, Principal principal, Model model) {
        if (principal != null) {
            User user = userService.findUserByUsername(principal.getName());
            for (long id : orderIds) {
                Cart cart = cartService.getOrderById(id);
                Picture picture = cart.getPicture();
                int newBalance = user.getBalance() - picture.getPrice();
                if(newBalance>=0) {
                    user.setBalance(newBalance);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Kiev"));
                    Date date = new Date();
                    cartService.setPurchaseDate(cart, dateFormat.format(date));
                    cartService.confirmOrder(id);
                    pictureService.update(picture);
                    userService.save(user);
                }else{
                    String msg = "Sorry, there is not enough money on your balance";
                    model.addAttribute("msg", msg);
                }
            }
        }
        return "redirect:/shop";
    }

    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public String shop(@ModelAttribute("msg") String msg, Model model, Principal principal) {
        if (principal != null) {
            User user = userService.findUserByUsername(principal.getName());
            List<Cart> userCarts = cartService.getUserCart(user);
            String name = principal.getName();
            model.addAttribute("login", name);
            model.addAttribute("orders", userCarts);
            model.addAttribute("msg", msg);

        }
        return "/shop";
    }


    @RequestMapping(value = "/remove/{idToRemove}", method = RequestMethod.GET)
    public String deletePicById(@PathVariable("idToRemove") long id, Model model) {
        model.addAttribute("idToRemove", id);
        return "redirect:/remove";
    }

    @RequestMapping("/remove")
    public String editGallery(@ModelAttribute("idToRemove") long idToRemove) {
        try {
            cartService.deleteOrderById(idToRemove);
            return "redirect:/shop";
        } catch (EntityNotFoundException e) {
        }
        return "redirect:/shop";
    }
}
