package com.javaweb.system.shiro;

import com.javaweb.common.utils.CommonUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 自定义验证类
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        String password = String.valueOf(utoken.getPassword());
        String md51 = CommonUtils.md5(password.getBytes());
        Object pwd = CommonUtils.md5((md51 + "IgtUdEQJyVevaCxQnY").getBytes());
        Object accountCredentials = getCredentials(info);
        return equals(pwd, accountCredentials);
    }
}
