package com.javaweb.system.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 系统角色 模块常量
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
public class RoleConstant {

    /**
     * 状态
     */
    public static Map<Integer, String> ROLE_STATUS_LIST = new HashMap<Integer, String>() {
        {
            put(1, "正常");
            put(2, "禁用");
        }
    };
}