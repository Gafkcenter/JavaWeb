package com.javaweb.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 品牌商列表Vo
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-05
 */
@Data
public class BrandCompanyListVo {

    /**
     * 品牌商ID
     */
    private Integer id;

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
     * 品牌商页的品牌商图片
     */
    private String imageUrl;

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
     * 创建时间
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