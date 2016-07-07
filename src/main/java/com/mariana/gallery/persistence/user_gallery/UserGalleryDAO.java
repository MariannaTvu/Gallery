package com.mariana.gallery.persistence.user_gallery;

import java.util.List;

public interface UserGalleryDAO {
    void add(UserGallery gallery);
    void delete(UserGallery gallery);
    UserGallery findOne(long id);
    List<UserGallery> list();
}
