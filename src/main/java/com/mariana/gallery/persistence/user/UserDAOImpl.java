package com.mariana.gallery.persistence.user;

import com.mariana.gallery.persistence.user_gallery.UserGallery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    public static final String JPQL_FIND_USER_BY_USERNAME = "SELECT u FROM User u WHERE u.login = :login";
    public static final String JPQL_FIND_USER_BY_USERGALLERY = "SELECT u FROM User u WHERE u.userGallery = :userGallery";
    public static final String PARAM_USERNAME = "login";
    public static final String PARAM_USERGALLERY = "userGallery";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(JPQL_FIND_USER_BY_USERNAME, User.class);
        query.setParameter(PARAM_USERNAME, username);

        return query.getSingleResult();
    }

    @Override
    public User saveUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Override
    public User setGallery(User user, UserGallery gallery){
        user.setUserGallery(gallery);
      return  entityManager.merge(user);
    }

    @Override
    public void setBio (User user, String bio){
        user.setBio(bio);
        entityManager.merge(user);
    }

    @Override
    public User findUserByGallery(UserGallery gallery){
        TypedQuery<User> query = entityManager.createQuery(JPQL_FIND_USER_BY_USERGALLERY, User.class);
        query.setParameter(PARAM_USERGALLERY, gallery);

        return query.getSingleResult();
    }
}