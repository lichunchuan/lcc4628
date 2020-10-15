package com.example.Controller;

import com.example.Result.Result;
import com.example.pojo.User;
import com.example.pojo.UserRole;
import com.example.service.RoleService;
import com.example.service.UserRoleService;
import com.example.service.UserService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    private String ERROR_KAPTCHA = "验证码不正确";
    /**
     * session中的验证码
     */
    private String SHIRO_VERIFY_SESSION = "verifySessionCode";

    @Resource
    private DefaultKaptcha defaultKaptcha;
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
    @GetMapping("/admin/index")
    public String index(){
        return "admin/index";
    }
    @GetMapping("/index")
    public String TemplateIndex(){
        return "index";
    }
//    @GetMapping("/success")
//    public String success(){
//        return "success";
//    }
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String login(String username, String password,String verifyCode, Model model){
        Result result=new Result();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        Subject currentUser = SecurityUtils.getSubject();
        model.addAttribute("currentUser",currentUser);
        // 获取session中的验证码
        String verCode = (String) currentUser.getSession().getAttribute(SHIRO_VERIFY_SESSION);
        if("".equals(verifyCode)||(!verCode.equals(verifyCode))){

            result.setData("验证码不正确");
            return result.toString();
        }
        try{
            //主体提交登录请求到securitymanager
            currentUser.login(token);
        }catch (IncorrectCredentialsException ice){
//            model.addAttribute("msg","密码不正确");
            result.setData("密码不正确");
            return result.toString();
        }catch (UnknownAccountException uae){
            result.setData("账号不存在");
            return result.toString();
        }catch (AuthenticationException ae){
            result.setData("账号或密码错误");
            return result.toString();
        }
        if(currentUser.isAuthenticated()){
            System.out.println("认证成功");
            String rolename = roleService.findRoleNameByUsername(username);
            result.setUserMsg(username);
            result.setRoleMsg(rolename);
//            model.addAttribute("username",username);
//            model.addAttribute("rolename",rolename);
            result.setData("认证成功");
            return result.toString();
        }else {
            token.clear();
            result.setData("返回到登录页面重新登录");
            return result.toString();
        }

    }
    //返回前端验证码字符串
    @GetMapping("/getNumber")
    @ResponseBody
    public String number(){
        String text = defaultKaptcha.createText();
        return text;
    }
    /**
     * 获取验证码
     * @param response
     */
    @GetMapping("/getCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        byte[] verByte = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute(SHIRO_VERIFY_SESSION,createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge,"jpg",jpegOutputStream);
        } catch (IllegalArgumentException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (IOException e){
            e.printStackTrace();
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        verByte = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(verByte);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute("msg","安全退出！");
        return "index";
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
//        if(username.equals(userService.findUserByUsername(username).getUsername())) {
//                result.setData("手机号重复了");
//        } else {
            userService.insertUser(user);
            Integer userId = userService.findUserByUsername(username).getId();
            System.out.println(userId);
            System.out.println(Integer.valueOf(user.getUsertype()));
            UserRole userRole = new UserRole();
            userRole.setRoleId(Integer.valueOf(user.getUsertype()));
            userRole.setUserId(userId);
            userRoleService.insertUserRole(userRole);
            result.setData("注册成功");
            return result;



    }
    @RequestMapping(value = "/findUserByUsername", method = RequestMethod.GET)
    @ResponseBody
    public User findUserByUsername(@RequestParam String username){
        User user = userService.findUserByUsername(username);
        return user;
    }
}
