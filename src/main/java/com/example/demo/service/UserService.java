package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    void save(User user);
    User findByUsername(String username);
}
