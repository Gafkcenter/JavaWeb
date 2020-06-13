package com.javaweb.admin.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 商品属性查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-08
 */
@Data
public class ProductAttributeQuery extends BaseQuery {

    /**
     * 属性名称
     */
    private String name;

    /**
     * 属性的类型：1规格 2属性
     */
    private Integer type;

}
