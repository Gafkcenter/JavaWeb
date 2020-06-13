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
 * 运费模版
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("pms_feight_template")
public class FeightTemplate extends BaseEntity {

    /**
     * 模板名称
     */
    private String name;

    /**
     * 计费类型：1按重量 2按件数
     */
    private Integer chargeType;

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

}