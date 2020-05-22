package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 系统人员查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
@Data
public class AdminQuery extends BaseQuery {

    /**
     * 姓名/手机号
     */
    private String keywords;

    /**
     * 性别:1男 2女 3保密
     */
    private Integer gender;

    /**
     * 状态：1正常 2禁用
     */
    private Integer status;

}
