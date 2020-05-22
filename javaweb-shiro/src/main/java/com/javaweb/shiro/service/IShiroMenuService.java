package com.javaweb.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaweb.shiro.entity.Menu;

import java.util.List;

/**
 * <p>
 * 登录日志 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
public interface IShiroMenuService extends IService<Menu> {

    /**
     * 根据人员ID获取菜单权限
     *
     * @param adminId 人员ID
     * @return
     */
    List<Menu> getMenuListByAdminId(Integer adminId);

}