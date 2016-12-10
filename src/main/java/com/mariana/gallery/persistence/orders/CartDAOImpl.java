package com.mariana.gallery.persistence.orders;

import static com.mariana.gallery.persistence.orders.Cart.*;
import com.mariana.gallery.persistence.picture.Picture;
import com.mariana.gallery.persistence.user.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
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
        TypedQuery<Cart> query = entityManager.createNamedQuery(JPQL_FIND_USER_CART, Cart.class);
        query.setParameter("user", user);
        query.setParameter("confirmedOrder", confirmedOrder);
        return query.getResultList();
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
        boolean confirmedOrder = true;
        TypedQuery<Cart> query = entityManager.createNamedQuery(JPQL_GET_USER_CONFIRMED_ORDERS, Cart.class);
        query.setParameter("user", user);
        query.setParameter("confirmedOrder", confirmedOrder);
        return query.getResultList();
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
        TypedQuery<Cart> query = entityManager.createQuery(JPQL_GET_ORDERS_OF_PICTURE, Cart.class);
        query.setParameter("picture", picture);
        return query.getResultList();
    }
}
