package com.mariana.gallery.persistence.user_gallery;

import java.util.List;

public interface UserGalleryDAO {
    UserGallery add(UserGallery gallery);

    void delete(UserGallery gallery);

    UserGallery findById(long id);

    List<UserGallery> allGalleriesList();
}
