package com.javaweb.system.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 字典 模块常量
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
public class DicConstant {

    /**
     * 状态
     */
    public static Map<Integer, String> DIC_STATUS_LIST = new HashMap<Integer, String>() {
        {
            put(1, "在用");
            put(2, "停用");
        }
    };
}