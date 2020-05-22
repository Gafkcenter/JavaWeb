package com.javaweb.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaweb.shiro.entity.Role;

import java.util.List;

/**
 * <p>
 * 登录日志 Mapper 接口
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
public interface ShiroRoleMapper extends BaseMapper<Role> {

    /**
     * 根据人员ID获取角色列表
     *
     * @param adminId 人员ID
     * @return
     */
    List<Role> getRoleListByAdminId(Integer adminId);

}
