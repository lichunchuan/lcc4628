package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;

@Mapper
public interface RoleMapper {
    @Select("select name from role where id in(select role_id from user_role where user_id in(select id from user where username=#{username}))")
    String findRoleNameByUsername(String username);
    @Select("select permission from role where id in(select role_id from user_role where user_id in(select id from user where username=#{username}))")
    Collection<String> findPermissionByUsername(String username);
}
