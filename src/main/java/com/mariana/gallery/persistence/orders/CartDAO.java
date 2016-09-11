package com.mariana.gallery.persistence.orders;

import com.mariana.gallery.persistence.picture.Picture;
import com.mariana.gallery.persistence.user.User;

import java.util.Date;
import java.util.List;

public interface CartDAO {
    Cart createOrder(Cart cart);

    void deleteOrderById(long id);

    void deleteOrdersByIds(long[] ids);

    List<Cart> getOrdersOfPicture(Picture picture);

    void confirmOrder(long id);

    int getSumCost(long id);

    List<Cart> findUserCart(User user);

    List<Cart> getOrdersByIds(long[] ids);

    Cart getOrderById(long id);

    List<Cart> getUserConfirmedOrders(User user);

    void setPurchaseDate(Cart order, String date);
}
