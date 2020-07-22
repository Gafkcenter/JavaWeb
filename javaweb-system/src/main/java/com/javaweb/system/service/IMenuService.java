package com.javaweb.system.service;

import com.javaweb.system.entity.Menu;
import com.javaweb.common.common.IBaseService;
import com.javaweb.system.vo.MenuListVo;

import java.util.List;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-07
 */
public interface IMenuService extends IBaseService<Menu> {

    /**
     * 根据人员ID获取菜单权限
     *
     * @param adminId 人员ID
     * @return
     */
    List<Menu> getMenuListByAdminId(Integer adminId);

    /**
     * 获取导航菜单
     *
     * @return
     */
    List<MenuListVo> getNavbarMenu();
}