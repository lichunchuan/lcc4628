package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    List<User> findUserByUsername(String username);


    //    插入用户（注册）
    @Insert("INSERT INTO `user`(`username`,`password`,`usertype`,`passwordVerify`) VALUES (#{username},#{password},#{usertype},#{passwordVerify})")
    void insertUser(User user);
}
