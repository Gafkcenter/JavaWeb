package com.javaweb.system.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 系统消息 模块常量
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-04
 */
public class MessageConstant {

    /**
     * 发送方式
     */
    public static Map<Integer, String> MESSAGE_TYPE_LIST = new HashMap<Integer, String>() {
        {
            put(1, "系统");
            put(2, "短信");
            put(3, "邮件");
            put(4, "微信");
            put(5, "推送");
        }
    };
    /**
     * 发送状态
     */
    public static Map<Integer, String> MESSAGE_SENDSTATUS_LIST = new HashMap<Integer, String>() {
        {
            put(1, "已发送");
            put(2, "未发送");
        }
    };
}