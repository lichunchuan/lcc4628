package com.example.mapper;

import com.example.pojo.UserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    @Insert("INSERT INTO `user_role`(`user_id`,`role_id`) VALUES (#{userId},#{roleId})")
    void insertUserRole(UserRole userRole);

    @Select("select * from `user_role` where user_id=#{user_id}")
    UserRole findUserRoleByUserId(Integer userId);
}
