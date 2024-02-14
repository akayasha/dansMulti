package com.test.dansmultipro.test.dansmultipro.service;

import com.test.dansmultipro.test.dansmultipro.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findById(String id);
    List<User> findAll();
    User create(User user);
}