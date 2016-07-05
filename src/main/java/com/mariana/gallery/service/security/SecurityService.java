package com.mariana.gallery.service.security;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}