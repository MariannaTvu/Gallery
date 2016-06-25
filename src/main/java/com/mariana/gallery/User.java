package com.mariana.gallery;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "userGallery")
    private UserGallery userGallery;

    @OneToOne
    @JoinColumn(name = "wallet")
    private Wallet wallet;

    @OneToMany(mappedBy="author", cascade=CascadeType.ALL)
    private List<Picture> pictures = new ArrayList<Picture>();

    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String comments;

    public User() {
          }

    public User(String comments, String email, String name, String password, List<Picture> pictures,
                String surname, UserGallery userGallery, String username) {
        this.comments = comments;
        this.email = email;
        this.name = name;
        this.password = password;
        this.pictures = pictures;
        this.surname = surname;
        this.userGallery = userGallery;
        this.username = username;

    }

    public String getComments() {
        return comments;
    }

    public User setComments(String comments) {
        this.comments = comments;
        return this;
    }

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

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserGallery getUserGallery() {
        return userGallery;
    }

    public User setUserGallery(UserGallery userGallery) {
        this.userGallery = userGallery;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public User setWallet(Wallet wallet) {
        this.wallet = wallet;
        return this;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public User setPictures(List<Picture> pictures) {
        this.pictures = pictures;
        return this;
    }
}
