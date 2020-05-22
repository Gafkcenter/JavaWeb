package com.javaweb.system.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 部门 模块常量
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-03
 */
public class DepConstant {

    /**
     * 类型
     */
    public static Map<Integer, String> DEP_TYPE_LIST = new HashMap<Integer, String>() {
        {
            put(1, "公司");
            put(2, "部门");
        }
    };
    /**
     * 是否有子级
     */
    public static Map<Integer, String> DEP_HASCHILD_LIST = new HashMap<Integer, String>() {
        {
            put(1, "是");
            put(2, "否");
        }
    };
}