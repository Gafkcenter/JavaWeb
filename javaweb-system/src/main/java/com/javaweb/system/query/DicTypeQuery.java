package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 字典类型查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
@Data
public class DicTypeQuery extends BaseQuery {

    /**
     * 字典名称
     */
    private String name;

}
