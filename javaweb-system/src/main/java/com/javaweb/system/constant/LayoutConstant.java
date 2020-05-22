package com.javaweb.system.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 布局 模块常量
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-03
 */
public class LayoutConstant {

    /**
     * 类型
     */
    public static Map<Integer, String> LAYOUT_TYPE_LIST = new HashMap<Integer, String>() {
        {
            put(1, "新闻资讯");
            put(2, "其他");
        }
    };
}