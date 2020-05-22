package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 广告位描述查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-01
 */
@Data
public class AdSortQuery extends BaseQuery {

    /**
     * 广告位名称
     */
    private String name;

    /**
     * 站点类型：1PC网站 2WAP手机站 3小程序 4APP移动端
     */
    private Integer platform;

}
