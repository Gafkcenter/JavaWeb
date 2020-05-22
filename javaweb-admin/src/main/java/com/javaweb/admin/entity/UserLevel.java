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
 * 会员等级
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ums_user_level")
public class UserLevel extends BaseEntity {

    /**
     * 级别名称
     */
    private String name;

    /**
     * 显示顺序
     */
    private Integer sort;

}