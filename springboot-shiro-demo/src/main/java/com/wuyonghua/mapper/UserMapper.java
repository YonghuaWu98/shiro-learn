package com.wuyonghua.mapper;

import com.wuyonghua.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {


    List<User> queryAllUser();
    void insertUser(User user);

    /**
        通过用户名查询用户数据
     */
    User queryUserByName(String username);

    int updateUser(User user);
}
