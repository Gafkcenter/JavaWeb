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
 * 商品属性值
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pms_product_attribute_value")
public class ProductAttributeValue extends BaseEntity {

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 商品属性ID
     */
    private Integer productAttributeId;

    /**
     * 商品属性名称
     */
    private String productAttributeName;

    /**
     * 商品属性类型
     */
    private Integer productAttributeType;

    /**
     * 手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开
     */
    private String productAttributeValue;

    /**
     * 所属店铺ID
     */
    private Integer storeId;

    /**
     * 排序号
     */
    private Integer sort;

}