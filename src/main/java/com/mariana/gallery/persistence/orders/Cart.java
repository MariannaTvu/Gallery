package com.mariana.gallery.persistence.orders;

import com.mariana.gallery.persistence.picture.Picture;
import com.mariana.gallery.persistence.user.User;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "picture")
    private Picture picture;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user")
    private User user;

    private Date date;
    private Date purchaseDate;

    private int sumCost;

    @Type(type = "yes_no")
    private boolean confirmedOrder;

    public Cart() {
    }

    public Cart(Date date, Picture picture, User user) {
        this.date = date;
        this.picture = picture;
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public Cart setDate(Date date) {
        this.date = date;
        return this;
    }

    public long getId() {
        return id;
    }

    public Cart setId(long id) {
        this.id = id;
        return this;
    }

    public Picture getPicture() {
        return picture;
    }

    public Cart setPicture(Picture picture) {
        this.picture = picture;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Cart setUser(User user) {
        this.user = user;
        return this;
    }

    public int getSumCost() {
        return sumCost;
    }

    public Cart setSumCost(int sumCost) {
        this.sumCost = sumCost;
        return this;
    }

    public boolean isConfirmedOrder() {
        return confirmedOrder;
    }

    public Cart setConfirmedOrder(boolean confirmedOrder) {
        this.confirmedOrder = confirmedOrder;
        return this;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public Cart setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }
}
