package com.javaweb.system.mapper;

import com.javaweb.system.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaweb.system.vo.MenuListVo;

import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-07
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 获取导航菜单
     * @param pid 上级ID
     * @return
     */
    List<MenuListVo> getNavbarMenu(Integer pid);
}
