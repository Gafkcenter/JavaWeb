package com.javaweb.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaweb.shiro.entity.OperLog;
import com.javaweb.shiro.mapper.ShiroOperLogMapper;
import com.javaweb.shiro.service.IShiroOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
@Service
public class ShiroOperLogServiceImpl extends ServiceImpl<ShiroOperLogMapper, OperLog> implements IShiroOperLogService {

    @Autowired
    private ShiroOperLogMapper shiroOperLogMapper;

    /**
     * 创建系统操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(OperLog operLog) {
        shiroOperLogMapper.insertOperlog(operLog);
    }
}