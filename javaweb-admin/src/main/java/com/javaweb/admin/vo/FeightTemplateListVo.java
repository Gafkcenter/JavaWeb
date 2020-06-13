package com.javaweb.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 运费模版列表Vo
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-09
 */
@Data
public class FeightTemplateListVo {

    /**
     * 运费模版ID
     */
    private Integer id;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 计费类型：1按重量 2按件数
     */
    private Integer chargeType;

    /**
     * 计费类型描述
     */
    private String chargeTypeName;

    /**
     * 首重kg
     */
    private BigDecimal firstWeight;

    /**
     * 首费（元）
     */
    private BigDecimal firstFee;

    /**
     * 后重量
     */
    private BigDecimal continueWeight;

    /**
     * 后费用
     */
    private BigDecimal continueFee;

    /**
     * 目的地（省、市）
     */
    private String dest;

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