package com.javaweb.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaweb.shiro.entity.OperLog;
import com.javaweb.common.common.IBaseService;

/**
 * <p>
 * 操作日志 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
public interface IShiroOperLogService extends IService<OperLog> {

    /**
     * 创建系统操作日志
     *
     * @param operLog 操作日志对象
     */
    void insertOperlog(OperLog operLog);

}