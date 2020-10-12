package com.example.Controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AuthorizingController {


//    @GetMapping("/login")
//    @RequiresPermissions("login")
//    public String login(){
//        return "login";
//    }
    @GetMapping("/jump")
    @RequiresRoles("user")
    public String jump(){
        return "jump";
    }
}
