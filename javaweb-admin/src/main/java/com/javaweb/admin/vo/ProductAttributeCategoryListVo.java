package com.javaweb.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 产品属性分类列表Vo
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-08
 */
@Data
public class ProductAttributeCategoryListVo {

    /**
     * 产品属性分类ID
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类图片
     */
    private String image;

    /**
     * 分类图片
     */
    private String imageUrl;

    /**
     * 店铺ID
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