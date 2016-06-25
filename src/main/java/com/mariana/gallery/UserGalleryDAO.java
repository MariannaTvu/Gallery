package com.mariana.gallery;

import com.mariana.gallery.UserGallery;

import java.util.List;

/**
 * Created by Maryana on 21.06.2016.
 */
public interface UserGalleryDAO {
    void add(UserGallery group);
    void delete(UserGallery group);
    UserGallery findOne(long id);
    List<UserGallery> list();
}
