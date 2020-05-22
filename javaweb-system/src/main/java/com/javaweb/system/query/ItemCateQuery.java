package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 栏目查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-03
 */
@Data
public class ItemCateQuery extends BaseQuery {

    /**
     * 栏目名称
     */
    private String name;

    /**
     * 有无封面:1有 2无
     */
    private Integer isCover;

    /**
     * 状态：1启用 2停用
     */
    private Integer status;

}
