package com.wuyonghua.learn;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.AbstractFactory;
import org.apache.shiro.mgt.SecurityManager;

/**
 * @Description TODO 测试加密api
 **/
public class EncryptionAuth {
    public static void main(String[] args) {
        //1. 加载配置文件
        AbstractFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2. 创建SecurityManager（安全管理器）
        SecurityManager securityManager = (SecurityManager) factory.getInstance();
        //3. 使securityManager可以被使用（生效）
        SecurityUtils.setSecurityManager(securityManager);
        //4. 获取Subject对象，当前用户
        Subject subject = SecurityUtils.getSubject();
        //5. 获取token
        UsernamePasswordToken token = new UsernamePasswordToken("wuyonghua", "wyh989795");
        //6. 开始登录认证
        try {
            subject.login(token);
            System.out.println("登录成功，欢迎 " + token.getUsername().toString());
        }catch (UnknownAccountException e) {
            e.printStackTrace();
            //返回用户不存在信息
            System.out.println("用户名不存在！");
        }catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            //返回密码错误信息
            System.out.println("用户名密码错误！");
        }catch (AuthenticationException e) {
            e.printStackTrace();
            //返回认证失败信息
        }
        //...其他自定义认证异常
    }

}
