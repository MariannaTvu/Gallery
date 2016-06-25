package com.mariana.gallery;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy="wallet")
    private User user;

    public Wallet() {
    }

    public Wallet(User user ) {
        this.user = user;

    }

    public long getId() {
        return id;
    }

    public Wallet setId(long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Wallet setUser(User user) {
        this.user = user;
        return this;
    }

}
