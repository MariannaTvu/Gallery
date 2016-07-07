package com.mariana.gallery.service.picture;

import com.mariana.gallery.persistence.picture.Picture;
import com.mariana.gallery.persistence.picture.PictureComment;
import com.mariana.gallery.persistence.picture.PictureDAO;
import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.persistence.user_gallery.UserGallery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PictureService {
    @Autowired
    private PictureDAO pictureDAO;

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

    @Transactional
    public List<Picture> sortPicturesByComments() {
        return pictureDAO.sortPicturesByComments();
    }

    @Transactional
    public List<Picture> sortPicturesByDate() {
        return pictureDAO.sortPicturesByDate();
    }

    @Transactional
    public List<Picture> random() {
        return pictureDAO.random();
    }

    @Transactional
    String getCommentAuthor(User user) {
        return pictureDAO.getCommentAuthor(user);
    }

    @Transactional
    public PictureComment setCommentDate(PictureComment comment, String date) {
        return pictureDAO.setCommentDate(comment, date);
    }

    @Transactional
    public void deletePicture(long[] ids) {
        pictureDAO.delete(ids);
    }

    @Transactional
    public void deletePictureById(long id) {
        pictureDAO.deletePictureById(id);
    }

    @Transactional(readOnly = true)
    public List<Picture> listPictures() {
        return pictureDAO.pictureList();
    }

    @Transactional
    public void addPicture(Picture picture) {
        pictureDAO.add(picture);
    }

    @Transactional
    public void addComment(PictureComment pictureComment) {
        pictureDAO.addComment(pictureComment);
    }

    @Transactional(readOnly = true)
    public List<Picture> getPicturesByGallery(UserGallery userGallery) {
        return pictureDAO.getByGallery(userGallery);
    }
}
