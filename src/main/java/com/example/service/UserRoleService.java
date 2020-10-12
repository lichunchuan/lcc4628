package com.example.service;

import com.example.pojo.UserRole;
import org.apache.ibatis.annotations.Param;

public interface UserRoleService {
    void insertUserRole(UserRole userRole);

    UserRole findUserRoleByUserId(Integer userId);
}
