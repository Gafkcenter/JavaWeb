package com.javaweb.admin.dto;

import lombok.Data;

import java.util.List;

/**
 * 生成商品SKU设置Dto
 */
@Data
public class GenerateSkuDto {

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 规格
     */
    private List<String> specs;

    /**
     * 属性
     */
    private List<List<String>> attrs;

    /**
     * SKU列表
     */
    private List<ProductSkuListDto> skuList;

}
