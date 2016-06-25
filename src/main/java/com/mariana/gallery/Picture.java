package com.mariana.gallery;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "userGallery")
    private UserGallery userGallery;
//
//
//    private String name;
//
    @ManyToOne
    @JoinColumn(name = "users")
    private User author;
//
//    private String category;
//    private String description;
//    private String comments;
//    private String likes;//int
//    private int price;
//    private boolean forSale;
//    private Date dateAdded;//long

    @Lob
    private byte[] bytes;//content

    public Picture() {
    }

    public Picture(byte[] bytes) {

        this.bytes = bytes;
    }//get/set лишнее, оставить как можно меньше

    public User getAuthor() {
        return author;
    }

    public Picture setAuthor(User author) {
        this.author = author;
        return this;
    }

    public UserGallery getUserGallery() {
        return userGallery;
    }

    public Picture setUserGallery(UserGallery userGallery) {
        this.userGallery = userGallery;
        return this;
    }
//    public User getAuthor() {
//        return author;
//    }
//
//    public Picture setAuthor(User author) {
//        this.author = author;
//        return this;
//    }
//
//    public byte[] getBytes() {
//        return bytes;
//    }
//
//    public Picture setBytes(byte[] bytes) {
//        this.bytes = bytes;
//        return this;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public Picture setCategory(String category) {
//        this.category = category;
//        return this;
//    }
//
//    public String getComments() {
//        return comments;
//    }
//
//    public Picture setComments(String comments) {
//        this.comments = comments;
//        return this;
//    }
//
//    public Date getDateAdded() {
//        return dateAdded;
//    }
//
//    public Picture setDateAdded(Date dateAdded) {
//        this.dateAdded = dateAdded;
//        return this;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public Picture setDescription(String description) {
//        this.description = description;
//        return this;
//    }
//
//    public boolean isForSale() {
//        return forSale;
//    }
//
//    public Picture setForSale(boolean forSale) {
//        this.forSale = forSale;
//        return this;
//    }

    public long getId() {
        return id;
    }

    public Picture setId(long id) {
        this.id = id;
        return this;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public Picture setBytes(byte[] bytes) {
        this.bytes = bytes;
        return this;
    }
//    public String getLikes() {
//        return likes;
//    }
//
//    public Picture setLikes(String likes) {
//        this.likes = likes;
//        return this;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Picture setName(String name) {
//        this.name = name;
//        return this;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public Picture setPrice(int price) {
//        this.price = price;
//        return this;
//    }
//
//    public UserGallery getUserGallery() {
//        return userGallery;
//    }
//
//    public Picture setUserGallery(UserGallery userGallery) {
//        this.userGallery = userGallery;
//        return this;
//    }
}


