package com.javaweb.system.service;

import com.javaweb.system.entity.Config;
import com.javaweb.common.common.IBaseService;

import java.util.List;

/**
 * <p>
 * 系统配置 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-03
 */
public interface IConfigService extends IBaseService<Config> {

    /**
     * 根据分组ID获取配置列表
     * @param groupId 分组ID
     * @return
     */
    List<Config> getConfigListByGroupId(Integer groupId);

}