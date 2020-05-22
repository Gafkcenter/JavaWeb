package com.javaweb.admin.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 商品分类 模块常量
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-18
 */
public class ProductCategoryConstant {

    /**
     * 状态
     */
    public static Map<Integer, String> PRODUCTCATEGORY_STATUS_LIST = new HashMap<Integer, String>() {
        {
            put(1, "显示");
            put(2, "隐藏");
        }
    };
}