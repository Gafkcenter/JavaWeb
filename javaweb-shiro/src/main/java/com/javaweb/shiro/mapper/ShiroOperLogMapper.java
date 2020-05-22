package com.javaweb.shiro.mapper;

import com.javaweb.shiro.entity.OperLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 操作日志 Mapper 接口
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
public interface ShiroOperLogMapper extends BaseMapper<OperLog> {

    /**
     * 创建系统操作日志
     *
     * @param operLog 操作日志对象
     */
    void insertOperlog(OperLog operLog);

}
