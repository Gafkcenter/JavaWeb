package com.javaweb.admin.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 会员地址 模块常量
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-04
 */
public class UserAddressConstant {

    /**
     * 默认地址
     */
    public static Map<Integer, String> USERADDRESS_ISDEFAULT_LIST = new HashMap<Integer, String>() {
        {
            put(1, "是");
            put(2, "否");
        }
    };
}