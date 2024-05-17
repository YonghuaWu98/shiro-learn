package wuyonghua;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * @Description TODO 自定义域reaml
 **/
class MyRealm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1. 获取用户输入的身份信息
        String principal = (String)token.getPrincipal();
        //2. 从所关联的域（MyRealm）中获取用户的信息
        //模拟数据库操作，判断是否存在该用户
        if (principal.equals("wuyonghua")) {
            //通过用户名信息从数据库中查询到该用户信息
            //直接模拟数据
            String username = "wuyonghua";
            String password = "wuh";
            return new SimpleAuthenticationInfo(username, password, this.getName());
        }



        return null;
    }
}
