package com.wuyonghua.controller;

import com.wuyonghua.pojo.User;
import com.wuyonghua.service.UserService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Security;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;
    /**
     * @description:  主页面
     */
    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/toLogin")
    public String login() {
        return "login";
    }

    @RequestMapping("/toRegister")
    public String register() {
        return "register";
    }

    /**
     * @description:  用户登录页面
     * @param: null
     */
    @RequestMapping("/login")
    public String toLogin(String username, String password, Model model) {
        System.out.println(username + "  " + password);
        //1 获取subject对象
        Subject subject = SecurityUtils.getSubject();
        //2 将用户数据封装到token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //3 用户登录
        try {
            subject.login(token);
            model.addAttribute("user", token.getPrincipal().toString());
            return "index";
        }catch (UnknownAccountException e) {
//            e.printStackTrace();
            model.addAttribute("msgAccErr", "用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e) {
//            e.printStackTrace();
            model.addAttribute("msgPwdErr", "密码错误");
            return "login";
        }catch (AuthenticationException e) {
//            e.printStackTrace();
            model.addAttribute("msg", "认证失败");
            return "login";

        }


    }


    /**
     * @description:  用户注册
     * @param: user
     */
    @PostMapping("/register")
    public String toRegister(User user) {
        //用户密码加密
        String password = user.getPassword();
        Md5Hash encryptPwd = new Md5Hash(password, "salt", 3);
        user.setPassword(encryptPwd.toString());
        //添加新用户
        userService.insertUser(user);
        return "login";
    }

    @PostMapping("/noauthor")
    public String toNoauthor() {
        return "noauthor";
    }




}
