package com.javaweb.admin.service;

import com.javaweb.admin.entity.ProductSku;
import com.javaweb.common.common.IBaseService;

import java.util.List;

/**
 * <p>
 * 商品SKU 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-11
 */
public interface IProductSkuService extends IBaseService<ProductSku> {

    /**
     * 根据商品ID获取SKU列表
     *
     * @param productId 商品ID
     * @return
     */
    List<ProductSku> getProductSkuList(Integer productId);

    /**
     * 根据商品ID删除SKU列表
     *
     * @param productId 商品ID
     */
    void deleteProductSkuByProductId(Integer productId);

}