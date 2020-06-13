package com.javaweb.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 商品属性值列表Vo
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-09
 */
@Data
public class ProductAttributeValueListVo {

    /**
     * 商品属性值ID
     */
    private Integer id;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;

}