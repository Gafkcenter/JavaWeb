package com.javaweb.admin.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 会员用户查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-04
 */
@Data
public class UserQuery extends BaseQuery {

    /**
     * 性别：1男 2女 3未知
     */
    private Integer gender;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 设备类型：1苹果 2安卓 3WAP站 4PC站 5微信小程序 6后台添加
     */
    private Integer device;

    /**
     * 用户状态：1登录 2登出
     */
    private Integer loginStatus;

    /**
     * 用户来源：1注册会员 2马甲会员
     */
    private Integer source;

    /**
     * 是否启用：1启用  2停用
     */
    private Integer status;

}
