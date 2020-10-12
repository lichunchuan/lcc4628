package com.example.Controller;

import com.example.Result.Result;
import com.example.pojo.User;
import com.example.pojo.UserRole;
import com.example.service.RoleService;
import com.example.service.UserRoleService;
import com.example.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private UserRoleService userRoleService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model){
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        Subject currentUser = SecurityUtils.getSubject();
        model.addAttribute("msg","currentUser");
        try{
            //主体提交登录请求到securitymanager
            currentUser.login(token);
        }catch (IncorrectCredentialsException ice){
            model.addAttribute("msg","密码不正确");
        }catch (UnknownAccountException uae){
            model.addAttribute("msg","账号不存在");
        }catch (AuthenticationException ae){
            model.addAttribute("msg","账号或密码有误");
        }
        if(currentUser.isAuthenticated()){
            System.out.println("认证成功");
            String rolename = roleService.findRoleNameByUsername(username);
            model.addAttribute("username",username);
            model.addAttribute("rolename",rolename);
            return "success";
        }else {
            token.clear();
            return "login";
        }

    }
    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute("msg","安全退出！");
        return "login";
    }
    @RequestMapping("/register")
    public String showRegister() {
        return "register";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result register(User user, Model model) throws IOException {
        Result result = new Result();
        String username = user.getUsername();
            userService.insertUser(user);
            Integer userId = userService.findUserByUsername(username).get(0).getId();
            System.out.println(userId);
            System.out.println(Integer.valueOf(user.getUsertype()));
            UserRole userRole=new UserRole();
            userRole.setRoleId(Integer.valueOf(user.getUsertype()));
            userRole.setUserId(userId);
            userRoleService.insertUserRole(userRole);
            result.setData("注册成功");
            return result;

    }
    @RequestMapping(value = "/findUserByUsername", method = RequestMethod.GET)
    @ResponseBody
    public List<User> findUserByUsername(@RequestParam String username){
        List<User> users = userService.findUserByUsername(username);
        return users;
    }
}
