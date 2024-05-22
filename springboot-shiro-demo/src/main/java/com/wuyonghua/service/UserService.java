package com.wuyonghua.service;

import com.wuyonghua.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    List<User> queryAllUsers();
    void insertUser(User user);

    /**
     * @description:通过用户名查询用户信息
     */
    User queryUserByName(String username);
}
