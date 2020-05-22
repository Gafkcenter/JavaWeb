package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 布局描述查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
@Data
public class LayoutDescQuery extends BaseQuery {

    /**
     * 推荐位名称
     */
    private String name;

}
