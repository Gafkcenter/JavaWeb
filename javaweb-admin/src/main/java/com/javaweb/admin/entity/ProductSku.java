package com.javaweb.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.common.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * <p>
 * 商品SKU
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pms_product_sku")
public class ProductSku extends BaseEntity {

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 商品图片
     */
    private String productPic;

    /**
     * SKU编码
     */
    private String skuCode;

    /**
     * 商品属性索引值 (attribute_value,attribute_value[,....])
     */
    private String attributeValue;

    /**
     * 属性对应的库存
     */
    private Integer stock;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 预警库存
     */
    private Integer lowStock;

    /**
     * 锁定库存
     */
    private Integer lockStock;

    /**
     * 属性金额
     */
    private BigDecimal price;

    /**
     * 成本价
     */
    private BigDecimal costPrice;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 图片
     */
    private String image;

    /**
     * 重量
     */
    private BigDecimal weight;

    /**
     * 体积
     */
    private BigDecimal volume;

    /**
     * 状态：1在用 2停用
     */
    private Integer status;

    /**
     * 活动类型：1商品 2秒杀 3砍价 4拼团
     */
    private Integer type;

    /**
     * 活动限购数量
     */
    private Integer quota;

    /**
     * 所属店铺ID
     */
    private Integer storeId;

}