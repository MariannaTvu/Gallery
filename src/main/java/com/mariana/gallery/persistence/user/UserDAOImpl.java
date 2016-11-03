package com.mariana.gallery.persistence.user;

import com.mariana.gallery.persistence.user_gallery.UserGallery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import static com.mariana.gallery.persistence.user.User.JPQL_FIND_USER_BY_USERGALLERY;
import static com.mariana.gallery.persistence.user.User.JPQL_FIND_USER_BY_USERNAME;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createNamedQuery(JPQL_FIND_USER_BY_USERNAME, User.class);
        query.setParameter("login", username);

        return query.getSingleResult();
    }

    @Override
    public User saveUser(User user) {
        entityManager.merge(user);
        return user;
    }

    @Override
    public void setGallery(User user, UserGallery gallery) {
        user.setUserGallery(gallery);
        entityManager.merge(user);
    }

    @Override
    public void setBio(User user, String bio) {
        user.setBio(bio);
        entityManager.merge(user);
    }

    @Override
    public User findUserByGallery(UserGallery gallery) {
        TypedQuery<User> query = entityManager.createNamedQuery(JPQL_FIND_USER_BY_USERGALLERY, User.class);
        query.setParameter("userGallery", gallery);

        return query.getSingleResult();
    }

    @Override
    public void setBalance(User user, int balance) {
        user.setBalance(balance);
        entityManager.merge(user);
    }
}