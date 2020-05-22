package com.javaweb.shiro.dto;

import lombok.Data;

/**
 * 系统登录Dto
 */
@Data
public class LoginDto {

    /**
     * 登录用户名
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 验证码
     */
    private String captcha;

    /**
     * 验证码KEY
     */
    private String key;

    /**
     * 是否记住我
     */
    private boolean rememberMe;

}
