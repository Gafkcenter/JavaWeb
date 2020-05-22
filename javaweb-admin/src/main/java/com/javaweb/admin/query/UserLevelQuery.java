package com.javaweb.admin.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 会员等级查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-04
 */
@Data
public class UserLevelQuery extends BaseQuery {

    /**
     * 级别名称
     */
    private String name;

}
