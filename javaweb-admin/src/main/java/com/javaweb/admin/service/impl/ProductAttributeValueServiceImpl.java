package com.javaweb.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.shiro.common.BaseServiceImpl;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.admin.constant.ProductAttributeValueConstant;
import com.javaweb.admin.entity.ProductAttributeValue;
import com.javaweb.admin.mapper.ProductAttributeValueMapper;
import com.javaweb.admin.query.ProductAttributeValueQuery;
import com.javaweb.admin.service.IProductAttributeValueService;
import com.javaweb.system.utils.AdminUtils;
import com.javaweb.admin.vo.ProductAttributeValueListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品属性值 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-09
 */
@Service
public class ProductAttributeValueServiceImpl extends BaseServiceImpl<ProductAttributeValueMapper, ProductAttributeValue> implements IProductAttributeValueService {

    @Autowired
    private ProductAttributeValueMapper productAttributeValueMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        ProductAttributeValueQuery productAttributeValueQuery = (ProductAttributeValueQuery) query;
        // 查询条件
        QueryWrapper<ProductAttributeValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<ProductAttributeValue> page = new Page<>(productAttributeValueQuery.getPage(), productAttributeValueQuery.getLimit());
        IPage<ProductAttributeValue> data = productAttributeValueMapper.selectPage(page, queryWrapper);
        List<ProductAttributeValue> productAttributeValueList = data.getRecords();
        List<ProductAttributeValueListVo> productAttributeValueListVoList = new ArrayList<>();
        if (!productAttributeValueList.isEmpty()) {
            productAttributeValueList.forEach(item -> {
                ProductAttributeValueListVo productAttributeValueListVo = new ProductAttributeValueListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, productAttributeValueListVo);
                // 添加人名称
                if (productAttributeValueListVo.getCreateUser() != null && productAttributeValueListVo.getCreateUser() > 0) {
                    productAttributeValueListVo.setCreateUserName(AdminUtils.getName((productAttributeValueListVo.getCreateUser())));
                }
                // 更新人名称
                if (productAttributeValueListVo.getUpdateUser() != null && productAttributeValueListVo.getUpdateUser() > 0) {
                    productAttributeValueListVo.setUpdateUserName(AdminUtils.getName((productAttributeValueListVo.getUpdateUser())));
                }
                productAttributeValueListVoList.add(productAttributeValueListVo);
            });
        }
        return JsonResult.success("操作成功", productAttributeValueListVoList, data.getTotal());
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        ProductAttributeValue entity = (ProductAttributeValue) super.getInfo(id);
        return entity;
    }

    /**
     * 添加或编辑记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(ProductAttributeValue entity) {
        return super.edit(entity);
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
        ProductAttributeValue entity = this.getById(id);
        if (entity == null) {
            return JsonResult.error("记录不存在");
        }
        return super.delete(entity);
    }

    /**
     * 根据商品ID获取规格和属性列表
     *
     * @param productId 商品ID
     * @return
     */
    @Override
    public List<ProductAttributeValue> getProductAttributeValueByProductId(Integer productId) {
        QueryWrapper<ProductAttributeValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");
        List<ProductAttributeValue> productAttributeValueList = productAttributeValueMapper.selectList(queryWrapper);
        return productAttributeValueList;
    }

    /**
     * 根据商品ID删除规格属性信息
     *
     * @param productId 商品ID
     */
    @Override
    public void deleteProductAttributeValueByProductId(Integer productId) {
        QueryWrapper<ProductAttributeValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        List<ProductAttributeValue> productAttributeValueList = productAttributeValueMapper.selectList(queryWrapper);
        if (!productAttributeValueList.isEmpty()) {
            productAttributeValueList.forEach(item -> {
                // 设置Mark=0
                item.setMark(0);
                productAttributeValueMapper.updateById(item);
            });
        }
    }
}