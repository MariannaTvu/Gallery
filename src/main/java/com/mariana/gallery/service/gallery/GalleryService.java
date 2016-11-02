package com.mariana.gallery.service.gallery;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mariana.gallery.persistence.picture.Picture;
import com.mariana.gallery.persistence.picture.PictureDAO;
import com.mariana.gallery.persistence.picture.PictureFilteringType;
import com.mariana.gallery.persistence.picture.PictureSortingType;
import com.mariana.gallery.persistence.user_gallery.UserGallery;
import com.mariana.gallery.persistence.user_gallery.UserGalleryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GalleryService {

    @Autowired
    private UserGalleryDAO userGalleryDAO;

    @Autowired
    private PictureDAO pictureDAO;

    @Transactional
    public UserGallery addUserGallery(UserGallery userGallery) {
        return userGalleryDAO.add(userGallery);
    }

    @Transactional
    public UserGallery getUserGalleryById(long id) {
        return userGalleryDAO.findById(id);
    }

    @Transactional
    public List<Picture> getPicturesByGallery(UserGallery userGallery, PictureFilteringType filteringType, PictureSortingType sortingType) {
        return userGallery.getPictures(filteringType, sortingType);
    }

    @Transactional
    public List<Picture> getAllPictures(PictureFilteringType filteringType, PictureSortingType sortingType) {
        List<Picture> pictures = pictureDAO.pictureList();
        List<Picture> res = (filteringType != null)
                ? Lists.newArrayList(Iterables.filter(pictures, filteringType))
                : new ArrayList<>(pictures);

        if (sortingType != null) {
            Collections.sort(res, sortingType);
        }
        return res;
    }
}
