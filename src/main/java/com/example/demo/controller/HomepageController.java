package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserRoleService;
import com.example.demo.service.UserService;
import com.example.demo.utils.EncryptedPasswordUtils;
import com.example.demo.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class HomepageController {
    private EncryptedPasswordUtils encryptedPasswordUtils = new EncryptedPasswordUtils();

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    private WebUtils webUtils = new WebUtils();

    @GetMapping("")
    public String showHomePage(){
        return "views/homepage";
    }

    @GetMapping("/login")
    public String showFormLogin(){

        return "views/login";
    }

    @GetMapping("/register")
    public String showFormRegister(Model model){
        User user = new User();
        List<Role> listRole = roleService.findAll();
        model.addAttribute("obj", user);
        model.addAttribute("listRole", listRole);
        return "views/register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("obj") User user, HttpServletRequest httpServletRequest, @RequestParam("password") String password, @RequestParam("password-confirm") String passwordConfirm, Model model){
        System.out.println("alo");
        Map<String,String[]> listParam = httpServletRequest.getParameterMap();
        List<Role> listRole = roleService.findAll();

        if (password.equals(passwordConfirm)){
            user.setEncrypedPassword(encryptedPasswordUtils.encryptePassword(password));
            System.out.println(user.getEncrypedPassword());
            try {
                userService.save(user);
            } catch (Exception ex){
                return "redirect:/register";
            }
        }

        for (Role role : listRole){
            if(listParam.containsKey("role-" + role.getId())){
                UserRole userRole = new UserRole();
                userRole.setRole(role);
                userRole.setUser(user);
                userRoleService.save(userRole);
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logoutSuccessful(){
        return "views/logout";
    }

    @GetMapping("/user-info")
    public String showUserInfo(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        List<UserRole> listUserRole = userRoleService.findByUser(user);
        String listUserRoleStr = webUtils.getAllRoleToStringForm(listUserRole);
        model.addAttribute("user", user);
        model.addAttribute("listUserRoleStr", listUserRoleStr);

        return "views/user-info";
    }

    @GetMapping("/403")
    public String show403(){
        return "views/403";
    }
}
