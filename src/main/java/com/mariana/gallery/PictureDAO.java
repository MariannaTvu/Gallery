package com.mariana.gallery;

import com.mariana.gallery.Picture;
import com.mariana.gallery.UserGallery;

import java.util.List;

/**
 * Created by Maryana on 21.06.2016.
 */
public interface PictureDAO {
    void add(Picture picture);
    void delete(Picture picture);
    void delete(long[] ids);
    List<Picture> list(UserGallery userGallery);
    List<Picture> list(String pattern);
}
