package com.example.demo.service;

import com.example.demo.dao.RoleDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.UserRoleDao;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);

        if (user == null){
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        System.out.println("Found User: " + user);

        List<UserRole> listUserRole = userRoleDao.findAllByUser(user);

        List<String> listUserRoleName = new ArrayList<>();
        for (UserRole userRole: listUserRole) {
            listUserRoleName.add(userRole.getRole().getName());
        }

        List<GrantedAuthority> grandList = new ArrayList<GrantedAuthority>();
        if (listUserRoleName != null){
            for (String role : listUserRoleName){
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
                grandList.add(grantedAuthority);
            }
        }

        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getUsername(), user.getEncrypedPassword(), grandList);

        return userDetails;
    }
}
