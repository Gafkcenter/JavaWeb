package com.javaweb.shiro.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.javaweb.common.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * <p>
 * 系统角色
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
public class Role extends BaseEntity {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色拥有的菜单ID，多个规则","隔开
     */
    private String rules;

    /**
     * 状态：1正常 2禁用
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;

}