package com.mariana.gallery.persistence.user_gallery;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mariana.gallery.persistence.picture.Picture;
import com.mariana.gallery.persistence.picture.PictureFilteringType;
import com.mariana.gallery.persistence.picture.PictureSortingType;
import com.mariana.gallery.persistence.user.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mariana.gallery.persistence.user_gallery.UserGallery.JPQL_FIND_ALL_GALLERIES;

@Entity
@Table(name = "userGallery")
@NamedQueries({
        @NamedQuery(
                name = JPQL_FIND_ALL_GALLERIES,
                query = "SELECT g FROM UserGallery g"
        )
})

public class UserGallery {
    public static final String JPQL_FIND_ALL_GALLERIES = "UserGallery.allGalleriesList";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany(mappedBy = "userGallery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Picture> pictures = new ArrayList<Picture>();

    @OneToOne(mappedBy = "userGallery", cascade=CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public UserGallery(User user) {
        this.name = user.getUsername();
        this.user = user;
    }

    public UserGallery() {
    }

    public UserGallery(String name, List<Picture> pictures, User user) {
        this.name = name;
        this.pictures = pictures;
        this.user = user;
    }
    public UserGallery(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Picture> getPictures(PictureFilteringType filteringType, PictureSortingType sortingType) {
        List<Picture> res = (filteringType != null)
                ? Lists.newArrayList(Iterables.filter(getPictures(), filteringType))
                : new ArrayList<>(getPictures());

        if (sortingType != null) {
            Collections.sort(res, sortingType);
        }

        return res;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public User getUser() {
        return user;
    }

    public UserGallery setUser(User user) {
        this.user = user;
        return this;
    }
}
