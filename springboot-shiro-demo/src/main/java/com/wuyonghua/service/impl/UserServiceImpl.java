package com.wuyonghua.service.impl;


import com.wuyonghua.mapper.UserMapper;
import com.wuyonghua.pojo.User;
import com.wuyonghua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public List<User> queryAllUsers() {
        return userMapper.queryAllUser();
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }
    /**
     * @description:  通过用户名查询用户信息
     * @param: username
     * @return: com.wuyonghua.pojo.User
     */
    @Override
    public User queryUserByName(String username) {
        return userMapper.queryUserByName(username);
    }
}
