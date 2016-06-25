package com.mariana.gallery;

import org.springframework.stereotype.Repository;

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
    public List<Picture> list(UserGallery userGallery) {//getByGallery
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
    public List<Picture> list(String pattern) {//имя не ок , getByNamePattern
        Query query = entityManager.createQuery("SELECT c FROM Picture c WHERE c.name LIKE :pattern", Picture.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return (List<Picture>) query.getResultList();
    } //разобрать
}