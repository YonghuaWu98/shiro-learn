package wuyonghua;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class ShiroQuickStart {

    public static void main(String[] args) {
        //1 获取并初始化SecurityManager(安全管理器)
        //1.1 加载ini配置文件
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //1.2 创建SecurityManager
        SecurityManager securityManager = factory.getInstance();
        //1.3 使其可以访问
        SecurityUtils.setSecurityManager(securityManager);
        //2 获取Subject对象，当前用户
        Subject subject = SecurityUtils.getSubject();
        //3. 创建token对象，web应用用户名密码从页面传递
        AuthenticationToken token = new UsernamePasswordToken("wuyonghua", "wyh");
        //4. 开始登录认证
        try {
            subject.login(token);
            //返回登录成功信息
            System.out.println("登录成功！");
            //授权操作，判断用户的权限
            System.out.println("用户是否拥有role1的权限：" + subject.hasRole("role1"));

        }catch (UnknownAccountException e) {
            e.printStackTrace();
            //返回用户不存在信息
            System.out.println("用户名不存在！");
        }catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            //返回密码错误信息
            System.out.println("密码错误！");
        }catch (AuthenticationException e) {
            e.printStackTrace();
            //返回认证失败信息
        }
        //...其他自定义认证异常

    }
}
