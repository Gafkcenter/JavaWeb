package com.javaweb.admin.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 商品 模块常量
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-09
 */
public class ProductConstant {

    /**
     * 是否热卖
     */
    public static Map<Integer, String> PRODUCT_ISHOT_LIST = new HashMap<Integer, String>() {
        {
            put(1, "是");
            put(2, "否");
        }
    };
    /**
     * 是否推荐
     */
    public static Map<Integer, String> PRODUCT_ISRECOMMAND_LIST = new HashMap<Integer, String>() {
        {
            put(1, "是");
            put(2, "否");
        }
    };
    /**
     * 是否新品
     */
    public static Map<Integer, String> PRODUCT_ISNEW_LIST = new HashMap<Integer, String>() {
        {
            put(1, "是");
            put(2, "否");
        }
    };
    /**
     * 审核状态
     */
    public static Map<Integer, String> PRODUCT_VERIFYSTATUS_LIST = new HashMap<Integer, String>() {
        {
            put(1, "已审核");
            put(2, "待审核");
        }
    };
    /**
     * 上架状态
     */
    public static Map<Integer, String> PRODUCT_STATUS_LIST = new HashMap<Integer, String>() {
        {
            put(1, "已上架");
            put(2, "已下架");
        }
    };
    /**
     * 产品服务
     */
    public static Map<Integer, String> PRODUCT_SERVICE_LIST = new HashMap<Integer, String>() {
        {
            put(1, "无忧退货");
            put(2, "快速退款");
            put(3, "免费包邮");
        }
    };
    /**
     * 促销类型
     */
    public static Map<Integer, String> PRODUCT_PROMOTIONTYPE_LIST = new HashMap<Integer, String>() {
        {
            put(0, "无优惠");
            put(1, "特惠促销价");
            put(2, "会员价");
            put(3, "阶梯价格");
            put(4, "满减价格");
        }
    };
    /**
     * 是否包邮
     */
    public static Map<Integer, String> PRODUCT_ISPOSTAGE_LIST = new HashMap<Integer, String>() {
        {
            put(1, "是");
            put(2, "否");
        }
    };
}