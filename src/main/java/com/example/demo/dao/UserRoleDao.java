package com.example.demo.dao;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleDao extends CrudRepository<UserRole, Integer> {
    List<UserRole> findAllByUser(User user);
}
