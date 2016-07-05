package com.mariana.gallery.persistence.picture;

import com.mariana.gallery.persistence.user_gallery.UserGallery;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Maryana on 21.06.2016.
 */
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

    String getPictureNameById(long id);

    List<Picture> sortPicturesByName();
}
