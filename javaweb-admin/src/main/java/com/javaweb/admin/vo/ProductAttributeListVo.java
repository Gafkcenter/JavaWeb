package com.javaweb.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 商品属性列表Vo
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-08
 */
@Data
public class ProductAttributeListVo {

    /**
     * 商品属性ID
     */
    private Integer id;

    /**
     * 商品属性分类ID
     */
    private Integer productAttributeCategoryId;

    /**
     * 商品属性分类名称
     */
    private String productAttributeCategoryName;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 属性的类型：1规格 2属性
     */
    private Integer type;

    /**
     * 属性的类型描述
     */
    private String typeName;

    /**
     * 排序号
     */
    private Integer sort;

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