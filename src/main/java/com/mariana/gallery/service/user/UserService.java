package com.mariana.gallery.service.user;

import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.persistence.user.UserDAO;
import com.mariana.gallery.persistence.user_gallery.UserGallery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Transactional
    public User findUserById(long id) {
        return userDAO.findUserById(id);
    }

    @Transactional
    public User findUserByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }

    @Transactional
    public User saveUser(User user, UserGallery gallery) {
        userDAO.saveUser(user);
        user.setUserGallery(gallery);
        return user;
    }
    @Transactional
    public User setUserGallery(User user, UserGallery gallery) {
        return userDAO.setGallery(user, gallery);
    }

    @Transactional
    public void addUserBio(User user, String bio) {
        userDAO.setBio(user, bio);
    }

    @Transactional
    public User findUserByGallery(UserGallery gallery) {
        return userDAO.findUserByGallery(gallery);
    }

}
