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
 * 会员地址
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ums_user_address")
public class UserAddress extends BaseEntity {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 收货人姓名
     */
    private String realname;

    /**
     * 收货人电话
     */
    private String mobile;

    /**
     * 收货人所在省ID
     */
    private Integer provinceId;

    /**
     * 收货人所在市ID
     */
    private Integer cityId;

    /**
     * 收货人所在区/县ID
     */
    private Integer districtId;

    /**
     * 收货人所在城市
     */
    private String cityArea;

    /**
     * 收货人详细地址
     */
    private String address;

    /**
     * 邮编
     */
    private Integer zipCode;

    /**
     * 默认地址：1是 2否
     */
    private Integer isDefault;

}