package com.javaweb.shiro.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 操作日志 模块常量
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
public class OperLogConstant {

    /**
     * 操作状态
     */
    public static Map<Integer, String> OPERLOG_STATUS_LIST = new HashMap<Integer, String>() {
        {
            put(0, "正常");
            put(1, "异常");
        }
    };
}