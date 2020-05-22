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
 * 栏目
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_item_cate")
public class ItemCate extends BaseEntity {

    /**
     * 栏目名称
     */
    private String name;

    /**
     * 父级ID
     */
    private Integer pid;

    /**
     * 站点ID
     */
    private Integer itemId;

    /**
     * 拼音(全)
     */
    private String pinyin;

    /**
     * 拼音(简)
     */
    private String code;

    /**
     * 有无封面:1有 2无
     */
    private Integer isCover;

    /**
     * 封面
     */
    private String cover;

    /**
     * 状态：1启用 2停用
     */
    private Integer status;

    /**
     * 备注
     */
    private String note;

    /**
     * 排序
     */
    private Integer sort;

}