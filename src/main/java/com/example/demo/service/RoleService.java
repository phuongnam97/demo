package com.example.demo.service;

import com.example.demo.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
}
