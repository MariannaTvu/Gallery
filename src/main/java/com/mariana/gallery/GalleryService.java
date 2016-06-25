package com.mariana.gallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GalleryService {
    @Autowired
    private PictureDAO pictureDAO;
    @Autowired
    private UserGalleryDAO userGalleryDAO;

    @Transactional
    public void addPicture(Picture picture) {
        pictureDAO.add(picture);
    }

    @Transactional
    public void addUserGallery(UserGallery userGallery) {
        userGalleryDAO.add(userGallery);
    }

    @Transactional
    public void deletePicture(long[] ids) {
        pictureDAO.delete(ids);
    }

    @Transactional
    public void deleteUserGallery(UserGallery userGallery) {
        userGalleryDAO.delete(userGallery);
    }

    @Transactional(readOnly=true)
    public List<UserGallery> listUserGallerys() {
        return userGalleryDAO.list();
    }

    @Transactional(readOnly=true)
    public List<Picture> listPictures(UserGallery userGallery) {
        return pictureDAO.list(userGallery);
    }

    @Transactional(readOnly=true)
    public UserGallery findUserGallery(long id) {
        return userGalleryDAO.findOne(id);
    }

    @Transactional(readOnly=true)
    public List<Picture> searchPictures(String pattern) {
        return pictureDAO.list(pattern);
    }
}
