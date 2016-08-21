package com.mariana.gallery.persistence.user;

import com.mariana.gallery.persistence.user_gallery.UserGallery;

public interface UserDAO {
    User findUserById(long id);

    User findUserByUsername(String username);

    User saveUser(User user);

    User setGallery(User user, UserGallery gallery);

    void setBio(User user, String bio);

    User findUserByGallery(UserGallery gallery);

    void setBalance(User user, int balance);
}
