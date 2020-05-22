package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 配置分组查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
@Data
public class ConfigGroupQuery extends BaseQuery {

    /**
     * 分组名称
     */
    private String name;

}
