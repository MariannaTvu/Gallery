package com.mariana.gallery.persistence.picture;

import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.persistence.user_gallery.UserGallery;

import java.util.List;

public interface PictureDAO {
    void add(Picture picture);

    void addComment(PictureComment comment);

    Picture update(Picture picture);

    PictureComment updateComment(PictureComment pictureComment);

    byte[] getPictureBytesById(long id);

    List<Picture> getByGallery(UserGallery userGallery);

    List<Picture> getByNamePattern(String pattern);

    List<Picture> pictureList();

    Picture getPictureById(long id);

    PictureComment setCommentDate(PictureComment comment, String date);

    String getPictureNameById(long id);

    List<Picture> random(int count);

    void deletePictureById(long id);

    void setPrice(Picture picture, int price);
}
