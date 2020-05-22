package com.javaweb.shiro.service;

import com.javaweb.common.utils.JsonResult;
import com.javaweb.shiro.dto.LoginDto;
import com.javaweb.shiro.entity.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统登录 服务类
 */
public interface IShiroLoginService {

    /**
     * 获取验证码
     *
     * @param response 请求响应
     * @return
     */
    JsonResult captcha(HttpServletResponse response);

    /**
     * 系统登录
     *
     * @param request  网络请求
     * @param loginDto 登录参数
     * @return
     */
    JsonResult login(HttpServletRequest request, LoginDto loginDto);


    /**
     * 退出登录
     *
     * @return
     */
    JsonResult logout();

    /**
     * 系统登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    Admin login(String username, String password);

}
