package com.javaweb.admin.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 商品查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-09
 */
@Data
public class ProductQuery extends BaseQuery {

    /**
     * 是否热卖：1是 2否
     */
    private Integer isHot;

    /**
     * 是否推荐：1是 2否
     */
    private Integer isRecommand;

    /**
     * 是否新品：1是 2否
     */
    private Integer isNew;

    /**
     * 审核状态：1已审核 2待审核
     */
    private Integer verifyStatus;

    /**
     * 上架状态：1已上架 2已下架
     */
    private Integer status;

    /**
     * 产品服务：1无忧退货 2快速退款 3免费包邮
     */
    private Integer service;

    /**
     * 促销类型：0没有促销使用原价 1使用促销价 2使用会员价 3使用阶梯价格 4使用满减价 5限时购
     */
    private Integer promotionType;

    /**
     * 是否包邮：1是 2否
     */
    private Integer isPostage;

}
