package com.javaweb.admin.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 文章管理查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-08-11
 */
@Data
public class ArticleQuery extends BaseQuery {

    /**
     * 文章标题
     */
    private String title;

    /**
     * 是否置顶：1已置顶 2未置顶
     */
    private Integer isTop;

    /**
     * 状态：1已审核 2待审核 3审核失败
     */
    private Integer status;

}
