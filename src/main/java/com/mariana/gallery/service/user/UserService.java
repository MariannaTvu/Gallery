package com.mariana.gallery.service.user;

public interface UserService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
