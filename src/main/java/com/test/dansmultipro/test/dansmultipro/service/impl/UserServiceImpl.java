package com.test.dansmultipro.test.dansmultipro.service.impl;

import com.test.dansmultipro.test.dansmultipro.model.entity.User;
import com.test.dansmultipro.test.dansmultipro.model.repository.UserRepository;
import com.test.dansmultipro.test.dansmultipro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(String id) {
        return userRepository.findByUsername(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
}
