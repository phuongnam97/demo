package com.example.demo.utils;

import com.example.demo.entity.UserRole;

import java.util.List;

public class WebUtils {
    public String getAllRoleToStringForm(List<UserRole> listUserRole){
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < listUserRole.size(); i++){
            sb.append(listUserRole.get(i).getRole().getName() + " ");
            if (i != listUserRole.size()- 1){
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
