package com.javaweb.admin.config;

import com.javaweb.admin.constant.BrandConstant;
import com.javaweb.admin.constant.ProductCategoryConstant;
import com.javaweb.admin.constant.ProductTagsConstant;
import com.javaweb.admin.constant.UserConstant;
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
            viewResolver.setStaticVariables(vars);
        }
    }
}
