package com.mariana.gallery.persistence.user;

import com.mariana.gallery.persistence.orders.Cart;
import com.mariana.gallery.persistence.user_gallery.UserGallery;

/**
 * Created by Maryana on 21.06.2016.
 */
public interface UserDAO {
    User findUserById(long id);

    User findUserByUsername(String username);

    User saveUser(User user);

    void deleteUser(User user);

    User setGallery(User user, UserGallery gallery);

    void setBio(User user, String bio);

    User findUserByGallery(UserGallery gallery);

    void setBalance(User user, int balance);
}
