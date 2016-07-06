package com.mariana.gallery.persistence.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    public static final String JPQL_FIND_USER_BY_USERNAME = "SELECT u FROM User u WHERE u.login = :login";
    public static final String PARAM_USERNAME = "login";

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
}