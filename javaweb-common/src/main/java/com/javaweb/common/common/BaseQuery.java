package com.javaweb.common.common;

import lombok.Data;

/**
 * 查询对象基类
 */
@Data
public class BaseQuery {
    /**
     * 页码(默认1)
     */
    private Integer pageIndex = 1;

    /**
     * 每页数(默认：20)
     */
    private Integer pageSize = 20;
}
