package com.javaweb.admin.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 商品品牌查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-05
 */
@Data
public class BrandQuery extends BaseQuery {

    /**
     * 状态：1启用 2停用
     */
    private Integer status;

}
