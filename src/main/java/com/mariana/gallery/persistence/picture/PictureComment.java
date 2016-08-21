package com.mariana.gallery.persistence.picture;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "picture_comments")
public class PictureComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "pictures")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Picture pictures;

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

    public Picture getPictures() {
        return pictures;
    }

    public PictureComment setPictures(Picture picture) {
        this.pictures = picture;
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
