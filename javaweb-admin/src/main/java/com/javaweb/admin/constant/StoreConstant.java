package com.javaweb.admin.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 店铺表 模块常量
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-10
 */
public class StoreConstant {

    /**
     * 店铺类型
     */
    public static Map<Integer, String> STORE_STORETYPE_LIST = new HashMap<Integer, String>() {
        {
            put(1, "公司");
            put(2, "个人");
        }
    };
    /**
     * 是否推荐
     */
    public static Map<Integer, String> STORE_STORERECOMMEND_LIST = new HashMap<Integer, String>() {
        {
            put(1, "是");
            put(2, "否");
        }
    };
    /**
     * 店铺状态
     */
    public static Map<Integer, String> STORE_STORESTATUS_LIST = new HashMap<Integer, String>() {
        {
            put(1, "正常");
            put(2, "关闭");
        }
    };
    /**
     * 是否平台店铺
     */
    public static Map<Integer, String> STORE_ISPLATFORM_LIST = new HashMap<Integer, String>() {
        {
            put(1, "是");
            put(2, "否");
        }
    };
    /**
     * 申请状态
     */
    public static Map<Integer, String> STORE_STATUS_LIST = new HashMap<Integer, String>() {
        {
            put(0, "信息已保存");
            put(60, "审核通过开店");
            put(50, "缴费审核失败");
            put(40, "缴费完成");
            put(30, "资料审核失败");
            put(20, "资料审核成功");
            put(10, "已提交申请");
        }
    };
}