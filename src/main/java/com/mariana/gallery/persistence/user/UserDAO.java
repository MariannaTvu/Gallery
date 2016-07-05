package com.mariana.gallery.persistence.user;

/**
 * Created by Maryana on 21.06.2016.
 */
public interface UserDAO {
    User findUserById(long id);
    User findUserByEmail(String email);
    User saveUser(User user);
    void deleteUser(User user);

}
