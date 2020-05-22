package com.javaweb.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 商品品牌列表Vo
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-05
 */
@Data
public class BrandListVo {

    /**
     * 商品品牌ID
     */
    private Integer id;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌logo
     */
    private String brandLogo;

    /**
     * 品牌logo
     */
    private String brandLogoUrl;

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
     * 专区大图
     */
    private String bigPicUrl;

    /**
     * 所属店铺
     */
    private Integer storeId;

    /**
     * 状态：1启用 2停用
     */
    private Integer status;

    /**
     * 状态描述
     */
    private String statusName;

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