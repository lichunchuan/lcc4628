package com.example.service.Impl;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

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
        String substring = UUID.randomUUID().toString().substring(0, 4);
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), substring, 2);
        user.setSalt(substring);
        user.setPassword(md5Hash.toString());
        userMapper.insertUser(user);
    }
}
