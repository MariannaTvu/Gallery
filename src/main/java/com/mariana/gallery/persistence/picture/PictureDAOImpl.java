package com.mariana.gallery.persistence.picture;

import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.persistence.user_gallery.UserGallery;
import org.hibernate.annotations.OrderBy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
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
        Picture c;
        c = entityManager.getReference(Picture.class, id);
        entityManager.remove(c);
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

        if (userGallery != null) {
            query = entityManager.createQuery("SELECT c FROM Picture c WHERE c.userGallery = :userGallery", Picture.class);
            query.setParameter("userGallery", userGallery);
        } else {
            query = entityManager.createQuery("SELECT c FROM Picture c", Picture.class);
        }

        return (List<Picture>) query.getResultList();
    }

    @Override
    public List<Picture> getByNamePattern(String pattern) {
        Query query = entityManager.createQuery("SELECT c FROM Picture c WHERE c.name LIKE :pattern", Picture.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return (List<Picture>) query.getResultList();
    } //разобрать

    @Override
    public List<Picture> pictureList() {
        Query query = entityManager.createQuery("SELECT g FROM Picture g", Picture.class);
        return (List<Picture>) query.getResultList();
    }

    @Override
    public List<PictureComment> getCommentsByPictureId(long id) {
        Query query = entityManager.createQuery("SELECT g FROM PictureComment g", Picture.class);
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
        Query query = entityManager.createQuery("SELECT g FROM Picture g ORDER BY g.name", Picture.class);
        return query.getResultList();
    }

    @Override
    public List<Picture> sortPicturesByComments() {
        Query query = entityManager.createQuery("SELECT g FROM Picture g ORDER BY g.comments.size DESC", Picture.class);
        return query.getResultList();
    }

    @Override
    public List<Picture> sortPicturesByDate() {
        Query query = entityManager.createQuery("SELECT g FROM Picture g ORDER BY g.dateAdded DESC", Picture.class);
        return query.getResultList();
    }

    @Override
    public List<Picture> random() {
        Query query = entityManager.createQuery("SELECT n FROM Picture n ORDER BY RAND()", Picture.class);
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
}