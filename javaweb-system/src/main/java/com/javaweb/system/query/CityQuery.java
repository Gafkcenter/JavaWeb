package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 城市查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-03
 */
@Data
public class CityQuery extends BaseQuery {

    /**
     * 城市名称
     */
    private String name;

    /**
     * 城市级别：1省份 2市区 3区县
     */
    private Integer level;

}
