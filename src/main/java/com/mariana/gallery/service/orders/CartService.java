package com.mariana.gallery.service.orders;

import com.mariana.gallery.persistence.orders.Cart;
import com.mariana.gallery.persistence.orders.CartDAO;
import com.mariana.gallery.persistence.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Cart> getUserCart(User user) {
        return cartDAO.findUserCart(user);
    }

    @Transactional
    public Cart getOrderById(long id) {
        return cartDAO.getOrderById(id);
    }

    @Transactional
    public void setPurchaseDate(Cart cart, String date) {
        cartDAO.setPurchaseDate(cart, date);
    }

    @Transactional
    public List<Cart> getUsersOrderHistory(User user) {
        return cartDAO.getUserConfirmedOrders(user);
    }
}
