package com.example.Controller;

import com.example.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Collection;

@Controller
public class RoleController {
    @Resource
    RoleService roleService;
    @RequestMapping(value = "/findRoleNameByUsername", method = RequestMethod.GET)
    @ResponseBody
    public String findRoleNameByUsername(String username){
        String roleNames = roleService.findRoleNameByUsername(username);
        return roleNames;
    }
    @RequestMapping(value = "/findPermissionByUsername", method = RequestMethod.GET)
    @ResponseBody
    public Collection findPermissionByUsername(String username){
        Collection<String> permission = roleService.findPermissionByUsername(username);
        return permission;
    }

}
