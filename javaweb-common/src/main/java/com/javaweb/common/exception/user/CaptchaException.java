package com.javaweb.common.exception.user;

/**
 * 验证码异常处理类
 */
public class CaptchaException extends UserException {

    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super("user.jcaptcha.error", null);
    }

}
