package com.javaweb.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 会员地址列表Vo
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-04
 */
@Data
public class UserAddressListVo {

    /**
     * 会员地址ID
     */
    private Integer id;

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

    /**
     * 默认地址描述
     */
    private String isDefaultName;

    /**
     * 创建人
     */
    private Integer createUser;

    /**
     * 创建人名称
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