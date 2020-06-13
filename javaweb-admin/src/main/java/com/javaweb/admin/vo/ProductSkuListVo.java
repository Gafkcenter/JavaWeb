package com.javaweb.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 商品SKU列表Vo
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-11
 */
@Data
public class ProductSkuListVo {

    /**
     * 商品SKUID
     */
    private Integer id;

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
     * 图片
     */
    private String imageUrl;

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
     * 活动类型描述
     */
    private String typeName;

    /**
     * 活动限购数量
     */
    private Integer quota;

    /**
     * 所属店铺ID
     */
    private Integer storeId;

    /**
     * 添加人
     */
    private Integer createUser;

    /**
     * 添加人名称
     */
    private String createUserName;

    /**
     * 添加时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新人
     */
    private Integer updateUser;

    /**
     * 更新人名称
     */
    private String updateUserName;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}