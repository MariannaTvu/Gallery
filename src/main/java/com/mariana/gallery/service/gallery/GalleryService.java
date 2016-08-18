package com.mariana.gallery.service.gallery;

import com.mariana.gallery.persistence.user_gallery.UserGallery;
import com.mariana.gallery.persistence.user_gallery.UserGalleryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GalleryService {

    @Autowired
    private UserGalleryDAO userGalleryDAO;

    @Transactional
    public UserGallery addUserGallery(UserGallery userGallery) {
        return userGalleryDAO.add(userGallery);
    }

    @Transactional
    public void deleteUserGallery(UserGallery userGallery) {
        userGalleryDAO.delete(userGallery);
    }

    @Transactional(readOnly = true)
    public List<UserGallery> listUserGallerys() {
        return userGalleryDAO.list();
    }

    @Transactional(readOnly = true)
    public UserGallery findUserGallery(long id) {
        return userGalleryDAO.findOne(id);
    }
}
