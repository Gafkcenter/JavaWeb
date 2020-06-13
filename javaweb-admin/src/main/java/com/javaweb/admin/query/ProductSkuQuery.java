package com.javaweb.admin.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 商品SKU查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-11
 */
@Data
public class ProductSkuQuery extends BaseQuery {

    /**
     * 活动类型：1商品 2秒杀 3砍价 4拼团
     */
    private Integer type;

}
