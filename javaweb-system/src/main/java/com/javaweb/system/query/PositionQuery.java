package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 岗位查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
@Data
public class PositionQuery extends BaseQuery {

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 状态：1正常 2停用
     */
    private Integer status;

}
