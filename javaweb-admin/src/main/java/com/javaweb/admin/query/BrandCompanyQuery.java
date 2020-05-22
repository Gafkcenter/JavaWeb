package com.javaweb.admin.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 品牌商查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-05
 */
@Data
public class BrandCompanyQuery extends BaseQuery {

    /**
     * 品牌商名称
     */
    private String name;

}
