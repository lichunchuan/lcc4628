package com.example.service.Impl;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> findUserByUsername(String username) {
        List<User> users = userMapper.findUserByUsername(username);
        return users;
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }
}
