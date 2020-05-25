package com.example.demo.service;

import com.example.demo.dao.UserRoleDao;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public void save(UserRole userRole) {
        userRoleDao.save(userRole);
    }

    @Override
    public List<UserRole> findByUser(User user) {
        return userRoleDao.findAllByUser(user);
    }
}
