package com.mariana.gallery.persistence.user_gallery;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserGalleryDAOImpl implements UserGalleryDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserGallery add(UserGallery userGallery) {
        entityManager.persist(userGallery);
        return userGallery;
    }

    @Override
    public void delete(UserGallery userGallery) {
        entityManager.remove(userGallery);
    }

    @Override
    public UserGallery findOne(long id) {
        return entityManager.getReference(UserGallery.class, id);
    }

    @Override
    public List<UserGallery> list() {
        Query query = entityManager.createQuery("SELECT g FROM UserGallery g", UserGallery.class);
        return (List<UserGallery>) query.getResultList();
    }
}
