package com.mariana.gallery.service.user;
import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.persistence.user.UserDAO;
import com.mariana.gallery.persistence.user_gallery.UserGallery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Transactional
    public User findUserById(long id) {
        return userDAO.findUserById(id);
    }

    @Transactional
    public User saveUser(User user, UserGallery gallery) {
        user.setUserGallery(gallery);
        userDAO.saveUser(user);
        return user;
    }

    @Transactional
    public User save (User user){
        userDAO.saveUser(user);
        return user;
    }

    @Transactional
    public void setUserGallery(User user, UserGallery gallery) {
        userDAO.setGallery(user, gallery);
    }

    @Transactional
    public void addUserBio(User user, String bio) {
        userDAO.setBio(user, bio);
    }

    @Transactional
    @Deprecated
    public User findUserByGallery(UserGallery gallery) {
        return userDAO.findUserByGallery(gallery);
    }

    @Transactional
    public void setBalance(User user, int balance) {
        userDAO.setBalance(user, balance);
    }
}
