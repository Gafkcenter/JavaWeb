package com.javaweb.admin.dto;

import lombok.Data;

import java.util.List;

/**
 * 更新商品SKU设置Dto
 */
@Data
public class UpdateSkuDto {

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * SKU列表
     */
    private List<ProductSkuListDto> skuList;

}
