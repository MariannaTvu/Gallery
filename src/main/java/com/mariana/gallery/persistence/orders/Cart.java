package com.mariana.gallery.persistence.orders;

import com.mariana.gallery.persistence.picture.Picture;
import com.mariana.gallery.persistence.user.User;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "picture")
    private Picture picture;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user")
    private User user;

    private String date;
    private String purchaseDate;

    private int sumCost;

    @Type(type="yes_no")
    private boolean confirmedOrder;

    public Cart() {
    }

    public Cart(String date, Picture picture, User user) {
        this.date = date;
        this.picture = picture;
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public Cart setDate(String date) {
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

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public Cart setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }
}
