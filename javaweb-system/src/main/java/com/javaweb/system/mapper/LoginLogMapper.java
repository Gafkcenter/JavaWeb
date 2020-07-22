package com.javaweb.system.mapper;

import com.javaweb.system.entity.LoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 登录日志 Mapper 接口
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-04
 */
public interface LoginLogMapper extends BaseMapper<LoginLog> {

    /**
     * 创建系统登录日志
     *
     * @param loginLog 登录信息
     */
    void insertLoginLog(LoginLog loginLog);

}
