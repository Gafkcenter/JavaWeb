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
 * 产品属性分类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pms_product_attribute_category")
public class ProductAttributeCategory extends BaseEntity {

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类图片
     */
    private String image;

    /**
     * 店铺ID
     */
    private Integer storeId;

    /**
     * 排序号
     */
    private Integer sort;

}