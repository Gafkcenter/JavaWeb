package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 系统角色查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
@Data
public class RoleQuery extends BaseQuery {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 状态：1正常 2禁用
     */
    private Integer status;

}
