package com.mariana.gallery.service.gallery;

import com.mariana.gallery.persistence.picture.Picture;
import com.mariana.gallery.persistence.picture.PictureComment;
import com.mariana.gallery.persistence.picture.PictureDAO;
import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.persistence.user.UserDAO;
import com.mariana.gallery.persistence.user_gallery.UserGallery;
import com.mariana.gallery.persistence.user_gallery.UserGalleryDAO;
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
    public User saveUser(User user) {
        return userDAO.saveUser(user);
    }


    @Transactional
    public void addPicture(Picture picture) {
        pictureDAO.add(picture);
    }

    @Transactional
    public void addComment(PictureComment pictureComment) {
        pictureDAO.addComment(pictureComment);
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

    @Transactional(readOnly = true)
    public List<UserGallery> listUserGallerys() {
        return userGalleryDAO.list();
    }

    @Transactional(readOnly = true)
    public List<Picture> listPictures() {
        return pictureDAO.pictureList();
    }

    @Transactional(readOnly = true)
    public List<Picture> getPicturesByGallery(UserGallery userGallery) {
        return pictureDAO.getByGallery(userGallery);
    }

    @Transactional(readOnly = true)
    public UserGallery findUserGallery(long id) {
        return userGalleryDAO.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<Picture> searchPictures(String pattern) {
        return pictureDAO.getByNamePattern(pattern);
    }

    @Transactional
    public byte[] getPictureBytesById(long id) {
        return pictureDAO.getPictureBytesById(id);
    }

    @Transactional
    public Picture update(Picture picture) {
        return pictureDAO.update(picture);
    }

    @Transactional
    public PictureComment updateComment(PictureComment pictureComment) {
        return pictureDAO.updateComment(pictureComment);
    }

    @Transactional
    public Picture getPictureById(long id) {
        return pictureDAO.getPictureById(id);
    }

    @Transactional
    public String getPictureName(long id) {
        return pictureDAO.getPictureNameById(id);
    }

    @Transactional
    public List<Picture> sortPicturesByName() {
        return pictureDAO.sortPicturesByName();
    }
}
