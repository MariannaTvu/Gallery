package com.mariana.gallery.service.orders;

import com.mariana.gallery.persistence.orders.Cart;
import com.mariana.gallery.persistence.orders.CartDAO;
import com.mariana.gallery.persistence.picture.Picture;
import com.mariana.gallery.persistence.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartDAO cartDAO;

    @Transactional
    public void createOrder(Cart cart) {
        cartDAO.createOrder(cart);
    }

    @Transactional
    public void deleteOrderById(long id) {
        cartDAO.deleteOrderById(id);
    }

    @Transactional
    public void confirmOrder(long id) {
        cartDAO.confirmOrder(id);
    }

    @Transactional
    public void getSumCost(long id) {
        cartDAO.getSumCost(id);
    }

    @Transactional
    public List<Cart> getUserCart(User user) {
        return cartDAO.findUserCart(user);
    }

    @Transactional
    public Cart getOrderById(long id) {
        return cartDAO.getOrderById(id);
    }

    @Transactional
    public void setPurchaseDate(Cart cart, Date date) {
        cartDAO.setPurchaseDate(cart, date);
    }

    @Transactional
    public List<Cart> getOrdersByIds(long[] ids) {
        return cartDAO.getOrdersByIds(ids);
    }

    @Transactional
    public List<Cart> getUsersOrderHistory(User user) {
        return cartDAO.getUserConfirmedOrders(user);
    }

    @Transactional
    public void deleteOrdersByIds(long[] ids) {
        cartDAO.deleteOrdersByIds(ids);
    }

    @Transactional
    public List<Cart> getOrdersOfPicture(Picture picture) {
      return cartDAO.getOrdersOfPicture(picture);
    }
}
