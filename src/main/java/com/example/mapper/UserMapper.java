package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    User findUserByUsername(String username);


    //    插入用户（注册）
    @Insert("INSERT INTO `user`(`username`,`password`,`usertype`,`passwordVerify`,`salt`) VALUES (#{username},#{password},#{usertype},#{passwordVerify},#{salt})")
    void insertUser(User user);

    @Select("select password from user where username=#{username}")
    String findPasswordByUsername(String username);
}
