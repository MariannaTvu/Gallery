package com.mariana.gallery.persistence.picture;

import com.mariana.gallery.persistence.orders.Cart;

import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.persistence.user_gallery.UserGallery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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
    public void delete(Picture picture) {
        entityManager.remove(picture);
    }

    @Override
    public void delete(long[] ids) {
        Picture c;
        for (long id : ids) {
            c = entityManager.getReference(Picture.class, id);
            entityManager.remove(c);
        }
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

            User user = getPictureAuthor(picture);
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
        Query query;
        boolean available = true;
        if (userGallery != null) {
            query = entityManager.createQuery("SELECT c FROM Picture c WHERE c.userGallery = :userGallery" +
                    " AND c.available = :available", Picture.class);
            query.setParameter("userGallery", userGallery);
            query.setParameter("available", available);
        } else {
            query = entityManager.createQuery("SELECT c FROM Picture c", Picture.class);
        }

        return (List<Picture>) query.getResultList();
    }

    @Override
    public List<Picture> getByNamePattern(String pattern) {
        boolean available = true;
        Query query = entityManager.createQuery("SELECT c FROM Picture c WHERE c.name LIKE :pattern" +
                " AND c.available = :available", Picture.class);
        query.setParameter("pattern", "%" + pattern + "%");
        query.setParameter("available", available);
        return (List<Picture>) query.getResultList();
    }

    @Override
    public List<Picture> pictureList() {
        boolean available = true;
        Query query = entityManager.createQuery("SELECT g FROM Picture g WHERE g.available = :available", Picture.class);
        query.setParameter("available", available);
        return (List<Picture>) query.getResultList();
    }

    @Override
    public User getPictureAuthor(Picture picture) {
        User user = picture.getAuthor();
        return user;
    }

    @Override
    public List<PictureComment> getCommentsByPictureId(long id) {
        Query query = entityManager.createQuery("SELECT g FROM PictureComment g", PictureComment.class);
        return query.getResultList();
    }


    @Override
    public String getPictureNameById(long id) {
        Picture c = entityManager.getReference(Picture.class, id);
        String name = c.getName();
        return name;
    }

    @Transactional
    @Override
    public Picture getPictureById(long id) {
        return entityManager.find(Picture.class, id);
    }

    @Override
    public List<Picture> sortPicturesByName() {
        boolean available = true;
        Query query = entityManager.createQuery("SELECT g FROM Picture g WHERE g.available = :available ORDER BY g.name", Picture.class);
        query.setParameter("available", available);
        return query.getResultList();
    }

    @Override
    public List<Picture> sortPicturesByComments() {
        boolean available = true;
        Query query = entityManager.createQuery("SELECT g FROM Picture g WHERE g.available = :available" +
                " ORDER BY g.comments.size DESC", Picture.class);
        query.setParameter("available", available);
        return query.getResultList();
    }

    @Override
    public List<Picture> sortPicturesByDate() {
        boolean available = true;
        Query query = entityManager.createQuery("SELECT g FROM Picture g WHERE g.available = :available" +
                " ORDER BY g.dateAdded DESC", Picture.class);
        query.setParameter("available", available);
        return query.getResultList();
    }

    @Override
    public List<Picture> random() {
        boolean available = true;
        Query query = entityManager.createQuery("SELECT n FROM Picture n WHERE n.available = :available" +
                " ORDER BY RAND()", Picture.class);
        query.setParameter("available", available);
        List<Picture> randomList = query.getResultList();
        return randomList;
    }

    @Override
    public String getCommentAuthor(User user) {
        return user.getLogin();
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