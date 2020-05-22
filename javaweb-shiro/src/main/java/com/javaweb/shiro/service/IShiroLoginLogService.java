package com.javaweb.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaweb.shiro.entity.LoginLog;

/**
 * <p>
 * 登录日志 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
public interface IShiroLoginLogService extends IService<LoginLog> {

    /**
     * 创建系统登录日志
     *
     * @param loginLog 访问日志对象
     */
    void insertLoginLog(LoginLog loginLog);

}