package com.javaweb.admin.service;

import com.javaweb.admin.dto.DeleteSkuDto;
import com.javaweb.admin.dto.GenerateSkuDto;
import com.javaweb.admin.dto.UpdateSkuDto;
import com.javaweb.admin.entity.Product;
import com.javaweb.common.common.IBaseService;
import com.javaweb.common.utils.JsonResult;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-09
 */
public interface IProductService extends IBaseService<Product> {

    /**
     * 生成SKU
     *
     * @param productSkuDto 参数
     * @return
     */
    JsonResult generateSku(GenerateSkuDto productSkuDto);

    /**
     * 更新SKU
     *
     * @param updateSkuDto 参数
     * @return
     */
    JsonResult updateSku(UpdateSkuDto updateSkuDto);

    /**
     * 删除SKU
     *
     * @param deleteSkuDto 参数
     * @return
     */
    JsonResult deleteSku(DeleteSkuDto deleteSkuDto);

}