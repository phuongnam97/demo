package com.example.demo.dao;

import com.example.demo.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao  extends CrudRepository<Role, Integer> {
    Role findById(int id);
    List<Role> findAll();
}
