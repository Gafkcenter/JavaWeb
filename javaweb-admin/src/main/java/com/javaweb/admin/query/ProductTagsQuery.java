package com.javaweb.admin.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 商品标签查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-18
 */
@Data
public class ProductTagsQuery extends BaseQuery {

    /**
     * 标签名
     */
    private String name;

    /**
     * 状态：1启用 2停用
     */
    private Integer status;

}
