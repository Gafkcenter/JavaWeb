package com.javaweb.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.shiro.entity.Menu;
import com.javaweb.shiro.mapper.ShiroMenuMapper;
import com.javaweb.shiro.service.IShiroMenuService;
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
public class ShiroMenuServiceImpl extends ServiceImpl<ShiroMenuMapper, Menu> implements IShiroMenuService {

    @Autowired
    private ShiroMenuMapper menuMapper;

    /**
     * 根据人员ID获取菜单权限列表
     *
     * @param adminId 人员ID
     * @return
     */
    @Override
    public List<Menu> getMenuListByAdminId(Integer adminId) {
        return menuMapper.getMenuListByAdminId(adminId);
    }
}