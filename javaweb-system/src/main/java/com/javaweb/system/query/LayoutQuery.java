package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 布局查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-03
 */
@Data
public class LayoutQuery extends BaseQuery {

    /**
     * 类型：1新闻资讯 2其他
     */
    private Integer type;

}
