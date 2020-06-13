package com.javaweb.admin.config;

import com.javaweb.admin.constant.*;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Thymeleaf模板配置
 */
@Configuration
public class ThymeleafAConfig {

    @Resource
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        if (viewResolver != null) {
            Map<String, Object> vars = new HashMap<>();

            /**
             * 会员性别
             */
            vars.put("USER_GENDER_LIST", UserConstant.USER_GENDER_LIST);
            /**
             * 会员设备类型
             */
            vars.put("USER_DEVICE_LIST", UserConstant.USER_DEVICE_LIST);
            /**
             * 会员登陆状态
             */
            vars.put("USER_LOGINSTATUS_LIST", UserConstant.USER_LOGINSTATUS_LIST);
            /**
             * 会员注册来源
             */
            vars.put("USER_SOURCE_LIST", UserConstant.USER_SOURCE_LIST);
            /**
             * 会员状态
             */
            vars.put("USER_STATUS_LIST", UserConstant.USER_STATUS_LIST);
            /**
             * 品牌状态
             */
            vars.put("BRAND_STATUS_LIST", BrandConstant.BRAND_STATUS_LIST);
            /**
             * 商品标签状态
             */
            vars.put("PRODUCTTAGS_STATUS_LIST", ProductTagsConstant.PRODUCTTAGS_STATUS_LIST);
            /**
             * 商品分类状态
             */
            vars.put("PRODUCTCATEGORY_STATUS_LIST", ProductCategoryConstant.PRODUCTCATEGORY_STATUS_LIST);
            /**
             * 商品属性类型
             */
            vars.put("PRODUCTATTRIBUTE_TYPE_LIST", ProductAttributeConstant.PRODUCTATTRIBUTE_TYPE_LIST);
            /**
             * 计费类型
             */
            vars.put("FEIGHTTEMPLATE_CHARGETYPE_LIST", FeightTemplateConstant.FEIGHTTEMPLATE_CHARGETYPE_LIST);
            /**
             * 商品是否热卖
             */
            vars.put("PRODUCT_ISHOT_LIST", ProductConstant.PRODUCT_ISHOT_LIST);
            /**
             * 商品是否推荐
             */
            vars.put("PRODUCT_ISRECOMMAND_LIST", ProductConstant.PRODUCT_ISRECOMMAND_LIST);
            /**
             * 商品是否新品
             */
            vars.put("PRODUCT_ISNEW_LIST", ProductConstant.PRODUCT_ISNEW_LIST);
            /**
             * 商品审核状态
             */
            vars.put("PRODUCT_VERIFYSTATUS_LIST", ProductConstant.PRODUCT_VERIFYSTATUS_LIST);
            /**
             * 商品上下架状态
             */
            vars.put("PRODUCT_STATUS_LIST", ProductConstant.PRODUCT_STATUS_LIST);
            /**
             * 商品促销类型
             */
            vars.put("PRODUCT_PROMOTIONTYPE_LIST", ProductConstant.PRODUCT_PROMOTIONTYPE_LIST);
            viewResolver.setStaticVariables(vars);
        }
    }
}
