package com.example.service;

import com.example.pojo.Role;

import java.util.Collection;
import java.util.List;

public interface RoleService {
    String findRoleNameByUsername(String username);

    Collection<String> findPermissionByUsername(String username);
}
