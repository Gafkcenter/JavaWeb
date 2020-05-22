package com.javaweb.admin.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 商品标签 模块常量
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-18
 */
public class ProductTagsConstant {

    /**
     * 状态
     */
    public static Map<Integer, String> PRODUCTTAGS_STATUS_LIST = new HashMap<Integer, String>() {
        {
            put(1, "启用");
            put(2, "停用");
        }
    };
}