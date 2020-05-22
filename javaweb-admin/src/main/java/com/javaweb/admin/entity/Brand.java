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
 * 商品品牌
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pms_brand")
public class Brand extends BaseEntity {

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌logo
     */
    private String brandLogo;

    /**
     * 品牌简介
     */
    private String brandIntro;

    /**
     * 品牌首字母
     */
    private String firstLetter;

    /**
     * 产品数量
     */
    private Integer productCount;

    /**
     * 品牌商ID
     */
    private Integer brandCompanyId;

    /**
     * 专区大图
     */
    private String bigPic;

    /**
     * 所属店铺
     */
    private Integer storeId;

    /**
     * 状态：1启用 2停用
     */
    private Integer status;

    /**
     * 排序号
     */
    private Integer sort;

}