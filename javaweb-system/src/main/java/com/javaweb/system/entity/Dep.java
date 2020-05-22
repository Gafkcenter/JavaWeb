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
 * 部门
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dep")
public class Dep extends BaseEntity {

    /**
     * 部门名称
     */
    private String name;

    /**
     * 上级ID
     */
    private Integer pid;

    /**
     * 类型：1公司 2部门
     */
    private Integer type;

    /**
     * 是否有子级：1是 2否
     */
    private Integer hasChild;

    /**
     * 排序
     */
    private Integer sort;

}