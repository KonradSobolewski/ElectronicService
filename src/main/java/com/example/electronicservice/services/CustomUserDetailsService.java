package com.example.electronicservice.services;

import com.example.electronicservice.dao.UserDao;
import com.example.electronicservice.models.CustomUserDetails;
import com.example.electronicservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    public Optional<User> getByUsername(String username) {
        return userDao.findByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return user.map(CustomUserDetails::new).get();
    }

    public Optional<User> getByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public Optional<User> getById(Long id) {
        return userDao.findById(id);
    }
}
