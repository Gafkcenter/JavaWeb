package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 字典查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
@Data
public class DicQuery extends BaseQuery {

    /**
     * 字典名称
     */
    private String title;

    /**
     * 状态：1在用 2停用
     */
    private Integer status;

}
