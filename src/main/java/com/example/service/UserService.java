package com.example.service;

import com.example.pojo.User;

import java.util.List;


public interface UserService {
    User findUserByUsername(String username);

    void insertUser(User user);
}
