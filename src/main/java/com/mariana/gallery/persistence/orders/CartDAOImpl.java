package com.mariana.gallery.persistence.orders;

import com.mariana.gallery.persistence.picture.Picture;
import com.mariana.gallery.persistence.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CartDAOImpl implements CartDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Cart createOrder(Cart cart) {
        entityManager.persist(cart);
        return cart;
    }

    @Override
    public void confirmOrder(long id) {
        Cart o;
        o = entityManager.find(Cart.class, id);
        o.setConfirmedOrder(true);
    }

    @Override
    public void deleteOrderById(long id) {
        Cart o = entityManager.find(Cart.class, id);
        entityManager.remove(entityManager.merge(o));

    }

    @Override
    public int getSumCost(long id) {
        Cart o;
        o = entityManager.getReference(Cart.class, id);
        return o.getSumCost();
    }

    @Override
    public List<Cart> findUserCart(User user) {
        boolean confirmedOrder = false;
        Query query = entityManager.createQuery("SELECT g FROM Cart g WHERE g.user = :user AND g.confirmedOrder = :confirmedOrder", Cart.class);
        query.setParameter("user", user);
        query.setParameter("confirmedOrder", confirmedOrder);
        return (List<Cart>) query.getResultList();
    }

    @Override
    public Cart getOrderById(long id) {
        return entityManager.find(Cart.class, id);
    }

    @Override
    public void setPurchaseDate(Cart order, String date) {
        order.setPurchaseDate(date);
        entityManager.merge(order);
    }

    @Override
    public List<Cart> getOrdersByIds(long[] ids) {
        Cart o;
        List<Cart> cartList = new ArrayList<>();
        for (long id : ids) {
            o = entityManager.find(Cart.class, id);
            cartList.add(o);
        }
        return cartList;
    }

    @Override
    public List<Cart> getUserConfirmedOrders(User user) {
        Query query;
        boolean confirmedOrder = true;
        query = entityManager.createQuery("SELECT c FROM Cart c WHERE c.user = :user AND c.confirmedOrder = :confirmedOrder", Cart.class);
        query.setParameter("user", user);
        query.setParameter("confirmedOrder", confirmedOrder);
        return (List<Cart>) query.getResultList();
    }

    @Override
    public void deleteOrdersByIds(long[] ids) {
        Cart c;
        for (long id : ids) {
            c = entityManager.getReference(Cart.class, id);
            entityManager.remove(c);
        }
    }

    @Override
    public List<Cart> getOrdersOfPicture(Picture picture) {
        Cart c;
        Query query;
        query = entityManager.createQuery("SELECT c FROM Cart c WHERE c.picture = :picture", Cart.class);
        query.setParameter("picture", picture);

        return (List<Cart>) query.getResultList();
    }
}
