package com.javaweb.system.service;

import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.dto.RolePermissionDto;
import com.javaweb.system.entity.RoleMenu;
import com.javaweb.common.common.IBaseService;


/**
 * <p>
 * 角色菜单关系 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-08
 */
public interface IRoleMenuService extends IBaseService<RoleMenu> {

    /**
     * 根据角色ID获取菜单列表
     *
     * @param roleId 角色ID
     * @return
     */
    JsonResult getRolePermissionByRoleId(Integer roleId);

    /**
     * 设置角色菜单权限
     *
     * @param rolePermissionDto 角色菜单权限
     * @return
     */
    JsonResult setRolePermission(RolePermissionDto rolePermissionDto);

}