package com.javaweb.system.entity;

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
 * 广告位描述
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_ad_sort")
public class AdSort extends BaseEntity {

    /**
     * 广告位名称
     */
    private String name;

    /**
     * 广告位描述
     */
    private String note;

    /**
     * 站点ID
     */
    private Integer itemId;

    /**
     * 栏目ID
     */
    private Integer cateId;

    /**
     * 广告页面位置
     */
    private Integer locId;

    /**
     * 站点类型：1PC网站 2WAP手机站 3小程序 4APP移动端
     */
    private Integer platform;

    /**
     * 广告位排序
     */
    private Integer sort;

}