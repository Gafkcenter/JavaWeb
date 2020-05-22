package com.javaweb.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 商品分类列表Vo
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-18
 */
@Data
public class ProductCategoryListVo {

    /**
     * 商品分类ID
     */
    private Integer id;

    /**
     * 店铺ID
     */
    private Integer storeId;

    /**
     * 上级ID
     */
    private Integer pid;

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
     * 级别：1一级 2二级 3三级 4四级
     */
    private Integer level;

    /**
     * 级别描述
     */
    private String levelName;

    /**
     * 备注
     */
    private String note;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态：1显示 2隐藏
     */
    private Integer status;

    /**
     * 状态描述
     */
    private String statusName;

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