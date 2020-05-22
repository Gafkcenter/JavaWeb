package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 登录日志查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-04
 */
@Data
public class LoginLogQuery extends BaseQuery {

    /**
     * 日志标题
     */
    private String title;

    /**
     * 登录状态：1成功 2失败
     */
    private Integer status;

    /**
     * 类型：1登录系统 2退出系统
     */
    private Integer type;

}
