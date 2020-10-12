package com.example.service.Impl;

import com.example.mapper.RoleMapper;
import com.example.pojo.Role;
import com.example.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleMapper roleMapper;
    @Override
    public String findRoleNameByUsername(String username) {
        String rolenames= roleMapper.findRoleNameByUsername(username);
        return rolenames;
    }

    @Override
    public Collection<String> findPermissionByUsername(String username) {
        Collection<String> permission = roleMapper.findPermissionByUsername(username);
        return permission;
    }
}
