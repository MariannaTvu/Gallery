package com.mariana.gallery.persistence.picture;

import com.mariana.gallery.persistence.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "picture_comments")
public class PictureComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "picture", nullable = false)
    private Picture picture;

    private String user;

    private String text;

    private String date;

    public PictureComment() {
    }

    public PictureComment(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public PictureComment setId(long id) {
        this.id = id;
        return this;
    }

    public Picture getPicture() {
        return picture;
    }

    public PictureComment setPicture(Picture picture) {
        this.picture = picture;
        return this;
    }

    public String getText() {
        return text;
    }

    public PictureComment setText(String text) {
        this.text = text;
        return this;
    }

    public String getDate() {
        return date;
    }

    public PictureComment setDate(String date) {
        this.date = date;
        return this;
    }

    public String getAuthor() {
        return user;
    }

    public PictureComment setAuthor(String user) {
        this.user = user;
        return this;
    }
}
