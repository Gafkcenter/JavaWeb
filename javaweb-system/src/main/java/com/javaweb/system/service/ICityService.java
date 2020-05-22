package com.javaweb.system.service;

import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.City;
import com.javaweb.common.common.IBaseService;

/**
 * <p>
 * 城市 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-03
 */
public interface ICityService extends IBaseService<City> {

    /**
     * 根据父级ID获取下级城市
     *
     * @param pid 父级ID
     * @return
     */
    JsonResult getCityListByPid(Integer pid);

    /**
     * 根据城市ID获取城市名称
     * @param cityId 城市ID
     * @param delimiter 拼接字符
     * @return
     */
    String getCityNameByCityId(Integer cityId, String delimiter);

}