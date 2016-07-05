package com.mariana.gallery.persistence.user;

import com.mariana.gallery.persistence.user_gallery.UserGallery;
import com.mariana.gallery.persistence.picture.Picture;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_gallery")
    private UserGallery userGallery;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Picture> pictures = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String login;
    private String email;
    private String password;

    //    private String comments;
//    private String name;
//    private String surname;
    public User() {
    }

    public User(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

//    public String getComments() {
//        return comments;
//    }
//
//    public User setComments(String comments) {
//        this.comments = comments;
//        return this;
//    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

//    public String getName() {
//        return name;
//    }
//
//    public User setName(String name) {
//        this.name = name;
//        return this;
//    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

//    public String getSurname() {
//        return surname;
//    }
//
//    public User setSurname(String surname) {
//        this.surname = surname;
//        return this;
//    }

    public UserGallery getUserGallery() {
        return userGallery;
    }

    public User setUserGallery(UserGallery userGallery) {
        this.userGallery = userGallery;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String username) {
        this.login = username;
        return this;
    }

     public List<Picture> getPictures() {
        return pictures;
    }

    public User setPictures(List<Picture> pictures) {
        this.pictures = pictures;
        return this;
    }

    public UserRole getRole() {
        return role;
    }

    public User setRole(UserRole role) {
        this.role = role;
        return this;
    }
}
