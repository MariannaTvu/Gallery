package com.mariana.gallery.persistence.picture;

import javax.persistence.*;

@Entity
@Table(name = "picture_comments")
public class PictureComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "picture")
    private Picture picture;

    private String text;

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
}
