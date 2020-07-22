package com.javaweb.system.mapper;

import com.javaweb.system.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaweb.system.vo.MenuListVo;
import org.apache.ibatis.annotations.Param;

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
     * 根据人员ID获取菜单列表
     *
     * @param adminId 人员ID
     * @return
     */
    List<Menu> getMenuListByAdminId(@Param("adminId") Integer adminId);

    /**
     * 获取导航菜单
     *
     * @param roleIds 角色ID集合(逗号分隔)
     * @param pid     上级ID
     * @return
     */
    List<MenuListVo> getNavbarMenu(@Param("roleIds") String roleIds, @Param("pid") Integer pid);
}
