package com.mariana.gallery.persistence.picture;

import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.persistence.user_gallery.UserGallery;

import javax.persistence.*;
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
    @Basic(fetch=FetchType.LAZY)
    private byte[] bytes;//content

    private String name;
    private String description;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name="picture_comments", joinColumns=@JoinColumn(name="picture_id"))
//    @Column(name="comment")
//    private List<String> comments = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "picture", cascade = CascadeType.ALL)
    private List<PictureComment> comments = new ArrayList<>();

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


}


