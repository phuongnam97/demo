package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;

import java.util.List;

public interface UserRoleService {
    void save(UserRole userRole);
    List<UserRole> findByUser(User user);
}
