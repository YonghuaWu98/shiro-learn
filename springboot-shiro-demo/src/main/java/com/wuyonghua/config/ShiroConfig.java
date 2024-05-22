package com.wuyonghua.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;

/**
 * @Description TODO shiro配置类
 **/
@Configuration
public class ShiroConfig {


    @Bean(name="realm")
    public MyRealm myRealm() {
        return new MyRealm();
    }
//    @Autowired
//    MyRealm myRealm;
    //配置SecurityManager(安全管理器）====> .ini文件
    @Bean(name="securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("realm") MyRealm myRealm) {
        //1 创建SecurityManager（安全管理器）
        DefaultWebSecurityManager defaultWebSecurityManager =
                new DefaultWebSecurityManager();
        //2 创建加密对象，设置相关属性
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //2.1 采用md5加密
        matcher.setHashAlgorithmName("md5");
        //2.2 迭代加密次数
        matcher.setHashIterations(3);
        //3 将加密对象存储到myRealm中
        myRealm.setCredentialsMatcher(matcher);
        //4 将myRealm存入到defaultWebSecurityManager对象
        defaultWebSecurityManager.setRealm(myRealm);
        //返回defaultWebSecurityManager对象，使安全管理器可用
        return defaultWebSecurityManager;
    }

    //配置shiro过滤器拦截范围
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        //创建过滤器
        ShiroFilterFactoryBean bean
                = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);
        /**
         * anon:无需认证就可以访问
         * authc:必须认证了才能访问
         * user：必须拥有记住我功能才能使用
         * perms：拥有对某个资源的权限才能访问
         * role：拥有某个角色权限才能访问
         *
         */
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();

        //授权，正常情况下，没有授权会跳转到为授权页面
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update", "perms[user:update]");
        filterMap.put("/user/delete", "perms[user:delete]");
        //设置认证可以访问的资源
        filterMap.put("/index", "authc");
        filterMap.put("/", "authc");
        filterMap.put("/user/*", "authc");
//        filterMap.put("/", "authc");
        //设置不认证可以访问的资源
//        filterMap.put("/toLogin", "noauth");
        filterMap.put("/toRegister", "anon");
//        definition.addPathDefinition("/toLogin", "anon");
//        definition.addPathDefinition("/toRegister", "anon");
//        //设置需要进行登录认证的拦截范围
//        definition.addPathDefinition("/**", "authc");

        //设置登录页面
        bean.setLoginUrl("/toLogin");
        //未授权页面
        bean.setUnauthorizedUrl("/noauthor");
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

}
