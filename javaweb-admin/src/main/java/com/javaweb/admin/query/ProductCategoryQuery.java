package com.javaweb.admin.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 商品分类查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-18
 */
@Data
public class ProductCategoryQuery extends BaseQuery {

    /**
     * 分类名称
     */
    private String name;

    /**
     * 状态：1显示 2隐藏
     */
    private Integer status;

}
