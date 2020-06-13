package com.javaweb.admin.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 运费模版查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-09
 */
@Data
public class FeightTemplateQuery extends BaseQuery {

    /**
     * 模板名称
     */
    private String name;

    /**
     * 计费类型：1按重量 2按件数
     */
    private Integer chargeType;

}
