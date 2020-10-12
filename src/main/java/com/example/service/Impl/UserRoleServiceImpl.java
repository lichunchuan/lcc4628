package com.example.service.Impl;

import com.example.mapper.UserRoleMapper;
import com.example.pojo.UserRole;
import com.example.service.UserRoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;
    @Override
    public void insertUserRole(UserRole userRole) {
        userRoleMapper.insertUserRole(userRole);
    }

    @Override
    public UserRole findUserRoleByUserId(Integer userId) {
        UserRole userRole = userRoleMapper.findUserRoleByUserId(userId);
        return userRole;
    }
}
