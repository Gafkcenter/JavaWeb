package com.javaweb.generator.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 代码生成查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-10
 */
@Data
public class GenTableQuery extends BaseQuery {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableComment;

}
