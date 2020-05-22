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
 * 布局
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_layout")
public class Layout extends BaseEntity {

    /**
     * 页面编号
     */
    private Integer itemId;

    /**
     * 页面的位置
     */
    private Integer locId;

    /**
     * 类型：1新闻资讯 2其他
     */
    private Integer type;

    /**
     * 对应的类型编号
     */
    private Integer typeId;

    /**
     * 图片路径
     */
    private String image;

    /**
     * 显示顺序
     */
    private Integer sort;

}