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
 * 商品
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pms_product")
public class Product extends BaseEntity {

    /**
     * 商品分类ID
     */
    private Integer productCategoryId;

    /**
     * 商品属性分类ID
     */
    private Integer productAttributeCategoryId;

    /**
     * 商品标题
     */
    private String productTitle;

    /**
     * 商品副标题
     */
    private String productSubTitle;

    /**
     * 商品简介
     */
    private String productInfo;

    /**
     * 商品标签
     */
    private String productTags;

    /**
     * 品牌ID
     */
    private Integer brandId;

    /**
     * 商品封面
     */
    private String cover;

    /**
     * 商品图片,连产品图片限制为5张，以逗号分割
     */
    private String image;

    /**
     * 主图视频链接
     */
    private String video;

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
     * 预告商品：1是 2否
     */
    private Integer isPreview;

    /**
     * 审核状态：1已审核 2待审核
     */
    private Integer verifyStatus;

    /**
     * 上架状态：1已上架 2已下架
     */
    private Integer status;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 商品售价（单位 元）
     */
    private BigDecimal price;

    /**
     * 赠送的成长值
     */
    private Integer giftGrowth;

    /**
     * 赠送的积分
     */
    private Integer giftIntegral;

    /**
     * 限制使用的积分数
     */
    private Integer useIntegralLimit;

    /**
     * 成本价(单位 元)
     */
    private BigDecimal costPrice;

    /**
     * 市场价
     */
    private BigDecimal originalPrice;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 库存预警值
     */
    private Integer lowStock;

    /**
     * 计量单位
     */
    private String unit;

    /**
     * 商品编号
     */
    private String productSn;

    /**
     * 体积(m³)
     */
    private BigDecimal volume;

    /**
     * 重量(KG)
     */
    private BigDecimal weight;

    /**
     * 产品服务：1无忧退货 2快速退款 3免费包邮
     */
    private String service;

    /**
     * 关键词
     */
    private String keywords;

    /**
     * 商品备注
     */
    private String note;

    /**
     * 促销开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date promotionStartTime;

    /**
     * 促销结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date promotionEndTime;

    /**
     * 促销价格
     */
    private BigDecimal promotionPrice;

    /**
     * 活动限购数量
     */
    private Integer promotionPerLimit;

    /**
     * 促销类型：0没有促销使用原价 1使用促销价 2使用会员价 3使用阶梯价格 4使用满减价 5限时购
     */
    private Integer promotionType;

    /**
     * 是否包邮：1是 2否
     */
    private Integer isPostage;

    /**
     * 邮费(单位 元)
     */
    private BigDecimal postage;

    /**
     * 运费模板ID
     */
    private Integer feightTemplateId;

    /**
     * 浏览量
     */
    private Integer browse;

    /**
     * 所属店铺ID
     */
    private Integer storeId;

}