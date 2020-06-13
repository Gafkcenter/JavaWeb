package com.javaweb.admin.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 运费模版 模块常量
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-09
 */
public class FeightTemplateConstant {

    /**
     * 计费类型
     */
    public static Map<Integer, String> FEIGHTTEMPLATE_CHARGETYPE_LIST = new HashMap<Integer, String>() {
        {
            put(1, "按重量");
            put(2, "按件数");
        }
    };
}