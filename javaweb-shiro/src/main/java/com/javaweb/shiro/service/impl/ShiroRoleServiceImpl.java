package com.javaweb.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.shiro.entity.Role;
import com.javaweb.shiro.mapper.ShiroRoleMapper;
import com.javaweb.shiro.service.IShiroRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 登录日志 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
@Service
public class ShiroRoleServiceImpl extends ServiceImpl<ShiroRoleMapper, Role> implements IShiroRoleService {

    @Autowired
    private ShiroRoleMapper roleMapper;

    /**
     * 根据人员ID获取角色列表
     *
     * @param adminId 人员ID
     * @return
     */
    @Override
    public List<Role> getRoleListByAdminId(Integer adminId) {
        return roleMapper.getRoleListByAdminId(adminId);
    }
}