package com.example.shiro;


import com.example.mapper.RoleMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class CustomRealm extends AuthorizingRealm {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //获取当前登录的用户
        User user= (User) principal.getPrimaryPrincipal();
        String username = user.getUsername();
        //通过SimpleAuthorizationInfo做授权
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        //添加角色
        simpleAuthorizationInfo.addRole(roleMapper.findRoleNameByUsername(username));
        //添加权限
        simpleAuthorizationInfo.addStringPermissions(roleMapper.findPermissionByUsername(username));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken Token) throws AuthenticationException {
        //获取用户输入的账号
        String username=(String) Token.getPrincipal();
        //通过username到数据库中查找到user实体
        User user =userMapper.findUserByUsername(username);
        if(user==null){
            return null;
        }
        //通过simpleAuthenticationinfo做身份处理
        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(user,user.getPassword(),ByteSource.Util.bytes(user.getSalt()),getName());
        if (user.getUsertype().equals("1")){
            System.out.println("该用户是商家用户");
        }else{
            System.out.println("该用户是买家用户");
        }
        return simpleAuthenticationInfo;

    }
}
