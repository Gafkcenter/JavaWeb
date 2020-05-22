package com.javaweb.system.controller;

import com.javaweb.common.utils.JsonResult;
import com.javaweb.shiro.dto.LoginDto;
import com.javaweb.shiro.service.IShiroLoginService;
import com.javaweb.shiro.utils.ShiroUtils;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 系统登录 控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-17
 */
@Controller
public class LoginController {

    @Autowired
    private IShiroLoginService loginService;

    /**
     * 登录首页
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 系统登录
     *
     * @param request  网络请求
     * @param loginDto 登录参数
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public JsonResult login(HttpServletRequest request, @RequestBody LoginDto loginDto) {
        ShiroUtils.logout();
        return loginService.login(request, loginDto);
    }

    /**
     * 获取验证码
     *
     * @param response
     * @return
     */
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(request, response);
    }

    /**
     * 未认证通过
     *
     * @return
     */
    @GetMapping("/unauth")
    public String unauth() {
        return "unauth";
    }

}
