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
 * 城市
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_city")
public class City extends BaseEntity {

    /**
     * 城市名称
     */
    private String name;

    /**
     * 城市级别：1省份 2市区 3区县
     */
    private Integer level;

    /**
     * 父级编号
     */
    private Integer pid;

    /**
     * 城市编号（区号）
     */
    private String citycode;

    /**
     * 父级地理编号
     */
    private String pAdcode;

    /**
     * 地理编号
     */
    private String adcode;

    /**
     * 城市坐标中心点经度（* 1e6）如果是中国，此值是 1e7
     */
    private Integer lng;

    /**
     * 城市坐标中心点纬度（* 1e6）
     */
    private Integer lat;

    /**
     * 排序号
     */
    private Integer sort;

}