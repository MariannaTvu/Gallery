package com.mariana.gallery.persistence.picture;

import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.persistence.user_gallery.UserGallery;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
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

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    @Basic(fetch = FetchType.LAZY)
    private String dateAdded;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] bytes;

    private String name;

    private int price;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String description;

    @Lob
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pictures", cascade = CascadeType.REMOVE)
    private List<PictureComment> comments = new ArrayList<>();

    @Type(type = "yes_no")
    private boolean available;

    public Picture() {
    }

    public Picture(byte[] bytes) {
        this.bytes = bytes;
    }

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

    public byte[] getBytes() {
        return bytes;
    }

    public Picture setBytes(byte[] bytes) {
        this.bytes = bytes;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Picture setDescription(String description) {
        this.description = description;
        return this;
    }

    public Picture setId(long id) {
        this.id = id;
        return this;
    }

    public Picture setName(String name) {
        this.name = name;
        return this;
    }

    public List<PictureComment> getPictureComments() {
        return comments;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public Picture setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Picture setPrice(int price) {
        this.price = price;
        return this;
    }

    public List<PictureComment> getComments() {
        return comments;
    }

    public Picture setComments(List<PictureComment> comments) {
        this.comments = comments;
        return this;
    }

    public boolean isAvailable() {
        return available;
    }

    public Picture setAvailable(boolean available) {
        this.available = available;
        return this;
    }
}


