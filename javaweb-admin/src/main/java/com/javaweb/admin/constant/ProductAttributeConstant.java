package com.javaweb.admin.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 商品属性 模块常量
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-08
 */
public class ProductAttributeConstant {

    /**
     * 属性的类型
     */
    public static Map<Integer, String> PRODUCTATTRIBUTE_TYPE_LIST = new HashMap<Integer, String>() {
        {
            put(1, "规格");
            put(2, "属性");
        }
    };
}