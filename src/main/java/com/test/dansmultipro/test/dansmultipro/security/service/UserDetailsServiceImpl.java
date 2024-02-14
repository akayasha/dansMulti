package com.test.dansmultipro.test.dansmultipro.security.service;


import com.test.dansmultipro.test.dansmultipro.model.entity.User;
import com.test.dansmultipro.test.dansmultipro.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.of(userRepository.findByUsername(username).get()).orElse(new User());
        return UserDetailsImpl.build(user);
    }

 }
