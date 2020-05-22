package com.javaweb.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaweb.shiro.entity.Menu;

import java.util.List;

/**
 * <p>
 * 登录日志 Mapper 接口
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
public interface ShiroMenuMapper extends BaseMapper<Menu> {

    /**
     * 根据人员ID获取菜单列表
     *
     * @param adminId 人员ID
     * @return
     */
    List<Menu> getMenuListByAdminId(Integer adminId);

}
