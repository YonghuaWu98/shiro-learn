package com.wuyonghua.controller;

import com.wuyonghua.pojo.User;
import com.wuyonghua.service.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description TODO
 **/

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/user/insert")
    public void insertUser(User user) {

        String password = user.getPassword();
        Md5Hash md2Pwd = new Md5Hash(password, "salt", 3);
        user.setPassword(md2Pwd.toString());
        userService.insertUser(user);
    }

    /**
     * @description:  查询所有用户信息
     * @param:
     */
    @RequiresRoles("manager")
    @RequestMapping("/user/query")
    @ResponseBody
    public List<User> queryAllUser() {
        List<User> users = userService.queryAllUsers();
        return users;
    }
    @RequiresRoles("manager")
    @RequestMapping("/user/add")
    public String addUser() {
//        List<User> users = userService.queryAllUsers();
        return "add";
    }
    @RequiresRoles("manager")
    @RequestMapping("/user/delete")
//    @ResponseBody
    public String deleteUser() {
//        List<User> users = userService.queryAllUsers();
        return "delete";
    }
    @RequiresRoles("manager")
    @RequestMapping("/user/update")
//    @ResponseBody
    public String updateUser() {
//        List<User> users = userService.queryAllUsers();
        return "update";
    }
}
