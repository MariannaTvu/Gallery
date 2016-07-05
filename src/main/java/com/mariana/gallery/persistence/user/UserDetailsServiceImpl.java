package com.mariana.gallery.persistence.user;

import com.mariana.gallery.service.gallery.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private GalleryService galleryService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.mariana.gallery.persistence.user.User account = galleryService.findUserByEmail(email);
        if (account == null)
            throw new UsernameNotFoundException(email + " not found");

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + account.getRole().name()));

        return new User(account.getEmail(), account.getPassword(), roles);
    }
}
