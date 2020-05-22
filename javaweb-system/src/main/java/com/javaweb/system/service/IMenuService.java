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
     * 获取导航菜单
     *
     * @return
     */
    List<MenuListVo> getNavbarMenu();
}