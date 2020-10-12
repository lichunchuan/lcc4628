package com.example.service;

import com.example.pojo.User;

import java.util.List;


public interface UserService {
    List<User> findUserByUsername(String username);

    void insertUser(User user);
}
