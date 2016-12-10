package com.mariana.gallery.persistence.orders;

import com.mariana.gallery.persistence.picture.Picture;
import com.mariana.gallery.persistence.user.User;
import org.hibernate.annotations.*;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import static com.mariana.gallery.persistence.orders.Cart.JPQL_FIND_USER_CART;
import static com.mariana.gallery.persistence.orders.Cart.JPQL_GET_ORDERS_OF_PICTURE;
import static com.mariana.gallery.persistence.orders.Cart.JPQL_GET_USER_CONFIRMED_ORDERS;


@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(
                name = JPQL_FIND_USER_CART,
                query = "SELECT g FROM Cart g WHERE g.user = :user AND g.confirmedOrder = :confirmedOrder"
        ),
        @NamedQuery(
                name = JPQL_GET_USER_CONFIRMED_ORDERS,
                query = "SELECT c FROM Cart c WHERE c.user = :user AND c.confirmedOrder = :confirmedOrder"
        ),
        @NamedQuery(
                name = JPQL_GET_ORDERS_OF_PICTURE,
                query = "SELECT c FROM Cart c WHERE c.picture = :picture"
        ),
})
public class Cart {

    public static final String JPQL_GET_USER_CONFIRMED_ORDERS = "Cart.getUserConfirmedOrders";
    public static final String JPQL_GET_ORDERS_OF_PICTURE = "Cart.getOrdersOfPicture";
    public static final String JPQL_FIND_USER_CART = "Cart.findUserCart";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "picture")
    private Picture picture;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User user;

    private String date;
    private String purchaseDate;

    private int sumCost;

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
