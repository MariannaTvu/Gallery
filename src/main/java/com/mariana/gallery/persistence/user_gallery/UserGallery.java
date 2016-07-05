package com.mariana.gallery.persistence.user_gallery;

import com.mariana.gallery.persistence.picture.Picture;
import com.mariana.gallery.persistence.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "userGallery")
public class UserGallery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany(mappedBy = "userGallery", cascade = CascadeType.ALL)
    private List<Picture> pictures = new ArrayList<Picture>();

    @OneToOne(mappedBy = "userGallery")
    private User user;

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

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
