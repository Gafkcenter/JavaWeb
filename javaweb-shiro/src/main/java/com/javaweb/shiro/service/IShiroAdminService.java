package com.javaweb.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaweb.shiro.entity.Admin;

/**
 * <p>
 * 登录日志 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
public interface IShiroAdminService extends IService<Admin> {

    /**
     * 根据用户名获取人员
     *
     * @param username 用户名
     * @return
     */
    Admin getAdminByUsername(String username);

}