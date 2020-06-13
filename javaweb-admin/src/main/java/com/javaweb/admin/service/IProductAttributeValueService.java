package com.javaweb.admin.service;

import com.javaweb.admin.entity.ProductAttributeValue;
import com.javaweb.common.common.IBaseService;

import java.util.List;

/**
 * <p>
 * 商品属性值 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-09
 */
public interface IProductAttributeValueService extends IBaseService<ProductAttributeValue> {

    /**
     * 根据商品ID获取规格及属性列表
     *
     * @param productId 商品ID
     * @return
     */
    List<ProductAttributeValue> getProductAttributeValueByProductId(Integer productId);

    /**
     * 根据商品ID删除规格属性信息
     *
     * @param productId 商品ID
     */
    void deleteProductAttributeValueByProductId(Integer productId);

}