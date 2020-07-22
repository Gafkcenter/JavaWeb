package com.javaweb.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.admin.dto.DeleteSkuDto;
import com.javaweb.admin.dto.GenerateSkuDto;
import com.javaweb.admin.dto.UpdateSkuDto;
import com.javaweb.admin.entity.*;
import com.javaweb.admin.mapper.*;
import com.javaweb.admin.service.IProductAttributeValueService;
import com.javaweb.admin.service.IProductSkuService;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.admin.constant.ProductConstant;
import com.javaweb.admin.query.ProductQuery;
import com.javaweb.admin.service.IProductService;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.utils.ShiroUtils;
import com.javaweb.system.utils.AdminUtils;
import com.javaweb.admin.vo.ProductListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-09
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductAttributeValueMapper productAttributeValueMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private IProductAttributeValueService productAttributeValueService;
    @Autowired
    private IProductSkuService productSkuService;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private BrandMapper brandMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        ProductQuery productQuery = (ProductQuery) query;
        // 查询条件
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        // 是否热卖：1是 2否
        if (productQuery.getIsHot() != null && productQuery.getIsHot() > 0) {
            queryWrapper.eq("is_hot", productQuery.getIsHot());
        }
        // 是否推荐：1是 2否
        if (productQuery.getIsRecommand() != null && productQuery.getIsRecommand() > 0) {
            queryWrapper.eq("is_recommand", productQuery.getIsRecommand());
        }
        // 是否新品：1是 2否
        if (productQuery.getIsNew() != null && productQuery.getIsNew() > 0) {
            queryWrapper.eq("is_new", productQuery.getIsNew());
        }
        // 审核状态：1已审核 2待审核
        if (productQuery.getVerifyStatus() != null && productQuery.getVerifyStatus() > 0) {
            queryWrapper.eq("verify_status", productQuery.getVerifyStatus());
        }
        // 上架状态：1已上架 2已下架
        if (productQuery.getStatus() != null && productQuery.getStatus() > 0) {
            queryWrapper.eq("status", productQuery.getStatus());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<Product> page = new Page<>(productQuery.getPage(), productQuery.getLimit());
        IPage<Product> data = productMapper.selectPage(page, queryWrapper);
        List<Product> productList = data.getRecords();
        List<ProductListVo> productListVoList = new ArrayList<>();
        if (!productList.isEmpty()) {
            productList.forEach(item -> {
                ProductListVo productListVo = new ProductListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, productListVo);
                // 商品封面地址
                if (!StringUtils.isEmpty(productListVo.getCover())) {
                    productListVo.setCoverUrl(CommonUtils.getImageURL(productListVo.getCover()));
                }
                // 商品图片,连产品图片限制为5张，以逗号分割地址
                if (!StringUtils.isEmpty(productListVo.getImage())) {
                    productListVo.setImageUrl(CommonUtils.getImageURL(productListVo.getImage()));
                }
                // 是否热卖描述
                if (productListVo.getIsHot() != null && productListVo.getIsHot() > 0) {
                    productListVo.setIsHotName(ProductConstant.PRODUCT_ISHOT_LIST.get(productListVo.getIsHot()));
                }
                // 是否推荐描述
                if (productListVo.getIsRecommand() != null && productListVo.getIsRecommand() > 0) {
                    productListVo.setIsRecommandName(ProductConstant.PRODUCT_ISRECOMMAND_LIST.get(productListVo.getIsRecommand()));
                }
                // 是否新品描述
                if (productListVo.getIsNew() != null && productListVo.getIsNew() > 0) {
                    productListVo.setIsNewName(ProductConstant.PRODUCT_ISNEW_LIST.get(productListVo.getIsNew()));
                }
                // 审核状态描述
                if (productListVo.getVerifyStatus() != null && productListVo.getVerifyStatus() > 0) {
                    productListVo.setVerifyStatusName(ProductConstant.PRODUCT_VERIFYSTATUS_LIST.get(productListVo.getVerifyStatus()));
                }
                // 上架状态描述
                if (productListVo.getStatus() != null && productListVo.getStatus() > 0) {
                    productListVo.setStatusName(ProductConstant.PRODUCT_STATUS_LIST.get(productListVo.getStatus()));
                }
                // 促销类型描述
                if (productListVo.getPromotionType() != null && productListVo.getPromotionType() > 0) {
                    productListVo.setPromotionTypeName(ProductConstant.PRODUCT_PROMOTIONTYPE_LIST.get(productListVo.getPromotionType()));
                }
                // 是否包邮描述
                if (productListVo.getIsPostage() != null && productListVo.getIsPostage() > 0) {
                    productListVo.setIsPostageName(ProductConstant.PRODUCT_ISPOSTAGE_LIST.get(productListVo.getIsPostage()));
                }
                // 商品分类
                if (productListVo.getProductCategoryId() != null && productListVo.getProductCategoryId() > 0) {
                    ProductCategory productCategory = productCategoryMapper.selectById(productListVo.getProductCategoryId());
                    if (productCategory != null) {
                        productListVo.setProductCategoryName(productCategory.getName());
                    }
                }
                // 品牌
                if (productListVo.getBrandId() != null && productListVo.getBrandId() > 0) {
                    Brand brand = brandMapper.selectById(productListVo.getBrandId());
                    if (brand != null) {
                        productListVo.setBrandName(brand.getBrandName());
                    }
                }
                // 添加人名称
                if (productListVo.getCreateUser() != null && productListVo.getCreateUser() > 0) {
                    productListVo.setCreateUserName(AdminUtils.getName((productListVo.getCreateUser())));
                }
                // 更新人名称
                if (productListVo.getUpdateUser() != null && productListVo.getUpdateUser() > 0) {
                    productListVo.setUpdateUserName(AdminUtils.getName((productListVo.getUpdateUser())));
                }
                productListVoList.add(productListVo);
            });
        }
        return JsonResult.success("操作成功", productListVoList, data.getTotal());
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        Product entity = (Product) super.getInfo(id);
        // 商品封面解析
        if (!StringUtils.isEmpty(entity.getCover())) {
            entity.setCover(CommonUtils.getImageURL(entity.getCover()));
        }
        return entity;
    }

    /**
     * 添加或编辑记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Product entity) {
        if (entity == null) {
            return JsonResult.error("实体对象不存在");
        }
        // 商品封面
        if (!StringUtils.isEmpty(entity.getCover()) && entity.getCover().contains(CommonConfig.imageURL)) {
            entity.setCover(entity.getCover().replaceAll(CommonConfig.imageURL, ""));
        }
        // 商品图片处理
        if (!StringUtils.isEmpty(entity.getImage())) {
            String[] strings = entity.getImage().split(",");
            List<String> stringList = new ArrayList<>();
            for (String s : strings) {
                if (s.contains(CommonConfig.imageURL)) {
                    stringList.add(s.replaceAll(CommonConfig.imageURL, ""));
                }
            }
            entity.setImage(org.apache.commons.lang3.StringUtils.join(stringList, ","));
        }
        boolean result = false;
        if (entity.getId() != null && entity.getId() > 0) {
            // 修改记录
            entity.setUpdateUser(ShiroUtils.getAdminId());
            entity.setUpdateTime(DateUtils.now());
            result = this.updateById(entity);

        } else {
            // 新增记录
            entity.setCreateUser(ShiroUtils.getAdminId());
            entity.setCreateTime(DateUtils.now());
            entity.setMark(1);
            result = this.save(entity);
        }
        if (!result) {
            return JsonResult.error();
        }
        return JsonResult.success("操作成功", entity.getId());
    }

    /**
     * 删除记录
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public JsonResult deleteById(Integer id) {
        if (id == null || id == 0) {
            return JsonResult.error("记录ID不能为空");
        }
        Product entity = this.getById(id);
        if (entity == null) {
            return JsonResult.error("记录不存在");
        }
        return super.delete(entity);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult setStatus(Product entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("记录ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("记录状态不能为空");
        }
        return super.setStatus(entity);
    }

    /**
     * 设置SKU
     *
     * @param productSkuDto 参数
     * @return
     */
    @Override
    public JsonResult generateSku(GenerateSkuDto productSkuDto) {
//        // 商品ID验证
//        if (productSkuDto.getProductId() == null || productSkuDto.getProductId() == 0) {
//            return JsonResult.error("商品ID不能为空");
//        }
//        // 验证规格是否为空
//        if (productSkuDto.getSpecs().size() == 0) {
//            return JsonResult.error("规格不存在");
//        }
//        // 验证规格属性是否为空
//        if (productSkuDto.getAttrs().size() == 0) {
//            return JsonResult.error("规格属性不存在");
//        }
//        // 判断规格和规格属性数组大小是否对等
//        if (productSkuDto.getSpecs().size() != productSkuDto.getAttrs().size()) {
//            return JsonResult.error("规格信息异常");
//        }
//        // 验证SKU列表是否为空
//        if (productSkuDto.getSkuList().size() == 0) {
//            return JsonResult.error("SKU信息不存在");
//        }
//
//        // 删除已存在的规格属性
//        productAttributeValueService.deleteProductAttributeValueByProductId(productSkuDto.getProductId());
//
//        // 创建商品规格属性值信息
//        for (int i = 0; i < productSkuDto.getSpecs().size(); i++) {
//            // 判断规格是否存在
//            ProductAttributeValue attributeValue = productAttributeValueMapper.selectOne(new LambdaQueryWrapper<ProductAttributeValue>()
//                    .eq(ProductAttributeValue::getProductId, productSkuDto.getProductId())
//                    .eq(ProductAttributeValue::getProductAttributeId, i + 1));
//            // 创建或更新规格信息
//            ProductAttributeValue productAttributeValue = new ProductAttributeValue();
//            productAttributeValue.setProductId(productSkuDto.getProductId());
//            productAttributeValue.setProductAttributeId(i + 1);
//            productAttributeValue.setProductAttributeName(productSkuDto.getSpecs().get(i));
//            productAttributeValue.setProductAttributeType(1);
//            productAttributeValue.setProductAttributeValue(org.apache.commons.lang3.StringUtils.join(productSkuDto.getAttrs().get(i), ","));
//            productAttributeValue.setSort(i);
//            if (attributeValue != null) {
//                // 恢复并更新
//                productAttributeValue.setId(attributeValue.getId());
//                productAttributeValue.setMark(1);
//                productAttributeValue.setUpdateUser(ShiroUtils.getAdminId());
//                productAttributeValue.setUpdateTime(DateUtils.now());
//                productAttributeValueMapper.updateById(productAttributeValue);
//            } else {
//                // 插入记录
//                productAttributeValue.setCreateUser(ShiroUtils.getAdminId());
//                productAttributeValue.setCreateTime(DateUtils.now());
//                productAttributeValueMapper.insert(productAttributeValue);
//            }
//        }
//
//        // 删除已存在SKU列表
//        productSkuService.deleteProductSkuByProductId(productSkuDto.getProductId());
//
//        // 创建SKU列表
//        for (ProductSkuListDto productSkuListDto : productSkuDto.getSkuList()) {
//            ProductSku productSku = new ProductSku();
//            productSku.setProductId(productSkuDto.getProductId());
//            productSku.setPrice(productSkuListDto.getPrice());
//            productSku.setCostPrice(productSkuListDto.getCostPrice());
//            productSku.setOriginalPrice(productSkuListDto.getOriginalPrice());
//            productSku.setStock(productSkuListDto.getStock());
//            productSku.setSkuCode(productSkuListDto.getSkuCode());
//            productSku.setVolume(productSkuListDto.getVolume());
//            productSku.setWeight(productSkuListDto.getWeight());
//            productSku.setAttributeValue(productSkuListDto.getAttributeValue());
//            productSku.setCreateUser(ShiroUtils.getAdminId());
//            productSku.setCreateTime(DateUtils.now());
//            productSkuMapper.insert(productSku);
//        }
        return JsonResult.success();
    }

    /**
     * 更新SKU信息
     *
     * @param updateSkuDto 参数
     * @return
     */
    @Override
    public JsonResult updateSku(UpdateSkuDto updateSkuDto) {
        return JsonResult.success();
//        // 商品ID验证
//        if (updateSkuDto.getProductId() == null || updateSkuDto.getProductId() == 0) {
//            return JsonResult.error("商品ID不能为空");
//        }
//        // 验证SKU列表是否为空
//        if (updateSkuDto.getSkuList().size() == 0) {
//            return JsonResult.error("SKU信息不存在");
//        }
//
//        // 遍历SKU信息
//        Integer totalNum = 0;
//        for (ProductSkuListDto productSkuListDto : updateSkuDto.getSkuList()) {
//            QueryWrapper<ProductSku> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("product_id", updateSkuDto.getProductId());
//            queryWrapper.eq("attribute_value", productSkuListDto.getAttributeValue());
//            queryWrapper.eq("mark", 1);
//            ProductSku productSkuInfo = productSkuMapper.selectOne(queryWrapper);
//            if (productSkuInfo == null) {
//                continue;
//            }
//            ProductSku productSku = new ProductSku();
//            productSku.setId(productSkuInfo.getId());
//            if (!StringUtils.isEmpty(productSkuListDto.getProductPic()) && productSkuListDto.getProductPic().contains(CommonConfig.imageURL)) {
//                productSku.setProductPic(productSkuListDto.getProductPic().replaceAll(CommonConfig.imageURL, ""));
//            } else {
//                productSku.setProductPic(productSkuListDto.getProductPic());
//            }
//            productSku.setPrice(productSkuListDto.getPrice());
//            productSku.setAttributeValue(productSkuListDto.getAttributeValue());
//            productSku.setCostPrice(productSkuListDto.getCostPrice());
//            productSku.setOriginalPrice(productSkuListDto.getOriginalPrice());
//            productSku.setStock(productSkuListDto.getStock());
//            productSku.setSkuCode(productSkuListDto.getSkuCode());
//            productSku.setVolume(productSkuListDto.getVolume());
//            productSku.setWeight(productSkuListDto.getWeight());
//            productSku.setStatus(productSkuListDto.getStatus());
//            productSku.setUpdateUser(ShiroUtils.getAdminId());
//            productSku.setUpdateTime(DateUtils.now());
//            Integer result = productSkuMapper.updateById(productSku);
//            if (result == 1) {
//                totalNum++;
//            }
//        }
//        return JsonResult.success(String.format("本地共更新【%s】条SKU记录", totalNum));
    }

    /**
     * 删除SKU
     *
     * @param deleteSkuDto 参数
     * @return
     */
    @Override
    public JsonResult deleteSku(DeleteSkuDto deleteSkuDto) {
//        QueryWrapper<ProductSku> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("product_id", deleteSkuDto.getProductId());
//        queryWrapper.eq("attribute_value", deleteSkuDto.getAttributeValue());
//        queryWrapper.eq("mark", 1);
//        ProductSku productSkuInfo = productSkuMapper.selectOne(queryWrapper);
//        if (productSkuInfo == null) {
//            return JsonResult.error("SKU信息不存在");
//        }
//        Integer result = productSkuMapper.deleteById(productSkuInfo.getId());
//        if (result == 0) {
//            return JsonResult.error("删除失败");
//        }
        return JsonResult.success("删除成功");
    }
}