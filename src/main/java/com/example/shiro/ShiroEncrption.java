package com.example.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroEncrption {
    public static String shiroEncryption(String password,String salt) {

        // shiro 自带的工具类生成salt
        //String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // 加密次数
        int times = 2;
        // 算法名称
        String algorithmName = "MD5";

        String encodedPassword = new SimpleHash(algorithmName,password,salt,times).toString();

        // 返回加密后的密码
        return encodedPassword;
    }

}
