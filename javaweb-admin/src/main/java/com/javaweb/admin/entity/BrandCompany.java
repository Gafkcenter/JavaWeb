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
 * 品牌商
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pms_brand_company")
public class BrandCompany extends BaseEntity {

    /**
     * 品牌商名称
     */
    private String name;

    /**
     * 品牌商简介
     */
    private String description;

    /**
     * 品牌商页的品牌商图片
     */
    private String image;

    /**
     * 排序号
     */
    private Integer sort;

}