package com.mariana.gallery.persistence.picture;

import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.persistence.user_gallery.UserGallery;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

public interface PictureDAO {
    void add(Picture picture);

    void addComment(PictureComment comment);

    void delete(Picture picture);

    void delete(long[] ids);

    Picture update(Picture picture);

    PictureComment updateComment(PictureComment pictureComment);

    byte[] getPictureBytesById(long id);

    List<Picture> getByGallery(UserGallery userGallery);

    List<Picture> getByNamePattern(String pattern);

    List<Picture> pictureList();

    Picture getPictureById(long id);

    List<PictureComment> getCommentsByPictureId(long id);

    String getCommentAuthor(User user);

    PictureComment setCommentDate(PictureComment comment, String date);

    String getPictureNameById(long id);

    List<Picture> sortPicturesByName();

    List<Picture> sortPicturesByComments();

    List<Picture> sortPicturesByDate();

    List<Picture> random();

    void deletePictureById(long id);

    void setPrice(Picture picture, int price);

    User getPictureAuthor(Picture picture);
}
