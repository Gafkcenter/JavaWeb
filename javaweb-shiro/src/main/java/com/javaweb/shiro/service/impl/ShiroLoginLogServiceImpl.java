package com.javaweb.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.shiro.entity.LoginLog;
import com.javaweb.shiro.mapper.ShiroLoginLogMapper;
import com.javaweb.shiro.service.IShiroLoginLogService;
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
public class ShiroLoginLogServiceImpl extends ServiceImpl<ShiroLoginLogMapper, LoginLog> implements IShiroLoginLogService {

    @Autowired
    private ShiroLoginLogMapper shiroLoginLogMapper;

    /**
     * 创建系统登录日志
     *
     * @param loginLog 访问日志对象
     */
    @Override
    public void insertLoginLog(LoginLog loginLog) {
        shiroLoginLogMapper.insertLoginLog(loginLog);
    }
}