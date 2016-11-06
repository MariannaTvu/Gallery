package com.mariana.gallery.persistence.user;

import com.mariana.gallery.persistence.orders.Cart;
import com.mariana.gallery.persistence.user_gallery.UserGallery;
import com.mariana.gallery.persistence.picture.Picture;
import org.hibernate.annotations.*;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import static com.mariana.gallery.persistence.user.User.JPQL_FIND_USER_BY_USERGALLERY;
import static com.mariana.gallery.persistence.user.User.JPQL_FIND_USER_BY_USERNAME;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(
                name = JPQL_FIND_USER_BY_USERNAME,
                query = "SELECT u FROM User u WHERE u.login = :login"

        ),
        @NamedQuery(
                name = JPQL_FIND_USER_BY_USERGALLERY,
                query = "SELECT u FROM User u WHERE u.userGallery = :userGallery"
        )

})
public class User {
    public static final String JPQL_FIND_USER_BY_USERNAME = "User.findUserByUsername";
    public static final String JPQL_FIND_USER_BY_USERGALLERY = "User.FindUserByGallery";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userGallery")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserGallery userGallery;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Picture> pictures = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Cart> orders = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserRole role;

    private String login;
    private String email;
    private String password;
    private String passwordConfirm;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String bio;
    private int balance;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getBalance() {
        return balance;
    }

    public User setBalance(int balance) {
        this.balance = balance;
        return this;
    }

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

    public String getUsername() {
        return login;
    }

    public User setLogin(String username) {
        this.login = username;
        return this;
    }

    public User setUsername(String username) {
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

    public String getBio() {
        return bio;
    }

    public User setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public List<Cart> getOrders() {
        return orders;
    }

    public User setOrders(List<Cart> orders) {
        this.orders = orders;
        return this;
    }


}
