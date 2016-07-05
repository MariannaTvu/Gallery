package com.mariana.gallery.persistence.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    public static final String JPQL_FIND_USER_BY_EMAIL = "SELECT u FROM User u WHERE u.email = :email";
    public static final String PARAM_EMAIL = "email";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUserByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery(JPQL_FIND_USER_BY_EMAIL, User.class);
        query.setParameter(PARAM_EMAIL, email);

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