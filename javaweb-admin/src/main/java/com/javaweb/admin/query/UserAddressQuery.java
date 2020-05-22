package com.javaweb.admin.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 会员地址查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-04
 */
@Data
public class UserAddressQuery extends BaseQuery {

    /**
     * 收货人电话
     */
    private String mobile;

    /**
     * 默认地址：1是 2否
     */
    private Integer isDefault;

}
