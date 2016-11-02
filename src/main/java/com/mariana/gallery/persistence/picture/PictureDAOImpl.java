package com.mariana.gallery.persistence.picture;

import com.mariana.gallery.persistence.orders.Cart;

import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.persistence.user_gallery.UserGallery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

import static com.mariana.gallery.persistence.picture.Picture.JPQL_GET_BY_NAME_PATTERN;
import static com.mariana.gallery.persistence.picture.Picture.JPQL_GET_BY_USER_GALLERY;
import static com.mariana.gallery.persistence.picture.Picture.JPQL_GET_ALL_PICTURES;
import static com.mariana.gallery.persistence.picture.Picture.JPQL_GET_RANDOM_PICTURES;

@Transactional
@Repository
public class PictureDAOImpl implements PictureDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Picture picture) {
        entityManager.persist(picture);
    }

    @Override
    public void addComment(PictureComment comment) {
        entityManager.persist(comment);
    }

    @Override
    public void deletePictureById(long id) {
        Picture picture = entityManager.find(Picture.class, id);
        if (picture != null) {
            Query queryOrdered;
            boolean confirmedOrder = true;
            queryOrdered = entityManager.createQuery("SELECT c FROM Cart c WHERE c.picture = :picture AND " +
                    "c.confirmedOrder = :confirmedOrder", Cart.class);
            queryOrdered.setParameter("picture", picture);
            queryOrdered.setParameter("confirmedOrder", confirmedOrder);
            List<Cart> orderHistory = (List<Cart>) queryOrdered.getResultList();

            for (Cart order : orderHistory) {
                order.setPicture(null);
                Picture pic = new Picture();
                pic.setPrice(picture.getPrice());
                pic.setAuthor(picture.getAuthor());
                pic.setName(picture.getName());
                pic.setAvailable(false);
                entityManager.persist(pic);
                order.setPicture(pic);
                entityManager.merge(order);
            }

            Query query;
            boolean notConfirmedOrder = false;
            query = entityManager.createQuery("SELECT c FROM Cart c WHERE c.picture = :picture AND " +
                    "c.confirmedOrder = :notConfirmedOrder", Cart.class);
            query.setParameter("picture", picture);
            query.setParameter("notConfirmedOrder", notConfirmedOrder);
            List<Cart> activeOrders = (List<Cart>) query.getResultList();

            for (Cart order : activeOrders){
            order.setPicture(null);
                entityManager.remove(order);
            }

            User user = picture.getAuthor();
            UserGallery userGallery = user.getUserGallery();

            int i = user.getPictures().indexOf(picture);
            user.getPictures().remove(i);
            entityManager.merge(user);
            int ind = userGallery.getPictures().indexOf(picture);
            userGallery.getPictures().remove(ind);
            entityManager.merge(userGallery);
            entityManager.merge(picture);
            entityManager.remove(picture);
        }
    }

    @Override
    public Picture update(Picture picture) {
        return entityManager.merge(picture);
    }

    @Override
    public PictureComment updateComment(PictureComment pictureComment) {
        return entityManager.merge(pictureComment);
    }

    @Override
    public byte[] getPictureBytesById(long id) {
        Picture c = entityManager.getReference(Picture.class, id);
        byte[] a = c.getBytes();
        return a;
    }

    @Override
    public List<Picture> getByGallery(UserGallery userGallery) {
        TypedQuery<Picture> query;
        boolean available = true;
        if (userGallery != null) {
            query = entityManager.createNamedQuery(JPQL_GET_BY_USER_GALLERY, Picture.class);
            query.setParameter("userGallery", userGallery);
            query.setParameter("available", available);
        } else {
            query = entityManager.createQuery("SELECT c FROM Picture c", Picture.class);
        }

        return query.getResultList();
    }

    @Override
    public List<Picture> getByNamePattern(String pattern) {
        return entityManager.createNamedQuery(JPQL_GET_BY_NAME_PATTERN, Picture.class)
                .setParameter("pattern", "%" + pattern + "%")
                .setParameter("available", true)
                .getResultList();
    }

    @Override
    public List<Picture> pictureList() {
        boolean available = true;
        TypedQuery<Picture> query = entityManager.createNamedQuery(JPQL_GET_ALL_PICTURES, Picture.class);
        query.setParameter("available", available);
        return query.getResultList();
    }

    @Override
    public String getPictureNameById(long id) {
        Picture c = entityManager.getReference(Picture.class, id);
        String name = c.getName();
        return name;
    }

    @Override
    public Picture getPictureById(long id) {
        return entityManager.find(Picture.class, id);
    }

    @Override
    public List<Picture> random(int count) {
        boolean available = true;
        TypedQuery<Picture> query = entityManager.createNamedQuery(JPQL_GET_RANDOM_PICTURES, Picture.class);
        query.setParameter("available", available);
        query.setMaxResults(count);
        List<Picture> randomList = query.getResultList();
        return randomList;
    }

    @Override
    public PictureComment setCommentDate(PictureComment comment, String date) {
        return comment.setDate(date);
    }

    @Override
    public void setPrice(Picture picture, int price) {
        picture.setPrice(price);
        entityManager.merge(picture);
    }
}

