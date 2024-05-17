package com.wuyonghua.learn;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

public class MyRealm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //自定义登录认证方法，shiro的login方法底层会调用该类的认证方法进行认证
        //需要配置自定义的realm生效，在ini文件中配置或在Springboot中配置
        //该方法只是获取进行对比的信息，认证逻辑还是按照shiro底层认证逻辑完成
        //1获取身份信息和凭据
        String principal = token.getPrincipal().toString();
        String credentials = new String((char[])token.getCredentials());
        System.out.println("用户认证信息：" + principal + "--------" + credentials);
        //2获取数据库中存储的用户信息
        if (principal.equals("wuyonghua")) {
            String password = "42627c91d64b21edd62994234743002f";
//            AuthenticationInfo info = new SimpleAuthenticationInfo(
//                    token.getPrincipal(),//用户身份信息
//                    password,//数据库中加盐的密码
////                    ByteSource.Util.bytes("salt"),//加盐方式
//                    token.getPrincipal().toString()//自定义的realm
//
//            );
             return new SimpleAuthenticationInfo(
                    principal,
                    password,
                    ByteSource.Util.bytes("salt"),
//                    token.getCredentials().toString()
                    this.getName()
            );

        }

        return null;

    }
}
