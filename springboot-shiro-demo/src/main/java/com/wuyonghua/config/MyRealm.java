package com.wuyonghua.config;

import com.wuyonghua.pojo.User;
import com.wuyonghua.service.UserService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.aop.AuthorizingAnnotationHandler;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description TODO 自定义Realm
 **/

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    //自定义授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("------------------执行了用户授权------------------》");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("user:add");
        //用户的权限信息在数据库中获取
        //首先要获取当前用户的信息，可以通过在认证返回的token中获得
        //创建subject对象
        Subject subject = SecurityUtils.getSubject();
//        //获取当前用户
        User user = (User)subject.getPrincipal();//报异常
        info.addRole(user.getRoles());
//        //获取并设置用户权限
        info.addStringPermission(user.getPermissions());
        return info;
    }
    //自定义认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("------------------执行了用户认证------------------》");
        //1 获取用户身份信息
        String principal = (String)token.getPrincipal();
        //2 调用业务层的方法获取用户信息
        User user = userService.queryUserByName(principal);
        //3 非空判断，将数据封装返回
        if (user != null) {
            return new SimpleAuthenticationInfo(
                    user,
                    user.getPassword(),
                    ByteSource.Util.bytes("salt"),
                    this.getName()
            );
        }
        return null;
    }
}
