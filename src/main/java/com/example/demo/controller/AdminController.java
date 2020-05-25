package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/list-user")
    public String showListUser(Model model){
        model.addAttribute("listObj", userDao.findAll());
        return "views/admin/list";
    }
}
