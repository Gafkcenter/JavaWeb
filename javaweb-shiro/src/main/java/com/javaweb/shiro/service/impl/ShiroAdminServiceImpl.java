package com.javaweb.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.shiro.entity.Admin;
import com.javaweb.shiro.mapper.ShiroAdminMapper;
import com.javaweb.shiro.service.IShiroAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录日志 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
@Service
public class ShiroAdminServiceImpl extends ServiceImpl<ShiroAdminMapper, Admin> implements IShiroAdminService {

    @Autowired
    private ShiroAdminMapper shiroAdminMapper;

    /**
     * 根据用户名获取人员
     *
     * @param username 用户名
     * @return
     */
    @Override
    public Admin getAdminByUsername(String username) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("mark", 1);
        Admin user = shiroAdminMapper.selectOne(queryWrapper);
        return user;
    }
}