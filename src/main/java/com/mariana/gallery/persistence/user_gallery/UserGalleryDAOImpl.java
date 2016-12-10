package com.mariana.gallery.persistence.user_gallery;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

import static com.mariana.gallery.persistence.user_gallery.UserGallery.JPQL_FIND_ALL_GALLERIES;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
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
    public UserGallery findById(long id) {
        return entityManager.find(UserGallery.class, id);
    }

    @Override
    public List<UserGallery> allGalleriesList() {
        TypedQuery<UserGallery> query = entityManager.createNamedQuery(JPQL_FIND_ALL_GALLERIES, UserGallery.class);
        return query.getResultList();
    }
}
