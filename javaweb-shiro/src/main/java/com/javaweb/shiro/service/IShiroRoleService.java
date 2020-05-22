package com.javaweb.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaweb.shiro.entity.Admin;
import com.javaweb.shiro.entity.Role;

import java.util.List;

/**
 * <p>
 * 登录日志 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
public interface IShiroRoleService extends IService<Role> {

    /**
     * 根据人员ID获取角色列表
     *
     * @param adminId 人员ID
     * @return
     */
    List<Role> getRoleListByAdminId(Integer adminId);

}