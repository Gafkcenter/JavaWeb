package com.javaweb.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.admin.entity.ProductAttributeCategory;
import com.javaweb.admin.mapper.ProductAttributeCategoryMapper;
import com.javaweb.admin.query.ProductAttributeCategoryQuery;
import com.javaweb.admin.service.IProductAttributeCategoryService;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.utils.AdminUtils;
import com.javaweb.admin.vo.ProductAttributeCategoryListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 产品属性分类 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-08
 */
@Service
public class ProductAttributeCategoryServiceImpl extends BaseServiceImpl<ProductAttributeCategoryMapper, ProductAttributeCategory> implements IProductAttributeCategoryService {

    @Autowired
    private ProductAttributeCategoryMapper productAttributeCategoryMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        ProductAttributeCategoryQuery productAttributeCategoryQuery = (ProductAttributeCategoryQuery) query;
        // 查询条件
        QueryWrapper<ProductAttributeCategory> queryWrapper = new QueryWrapper<>();
        // 分类名称
        if (!StringUtils.isEmpty(productAttributeCategoryQuery.getName())) {
            queryWrapper.like("name", productAttributeCategoryQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<ProductAttributeCategory> page = new Page<>(productAttributeCategoryQuery.getPage(), productAttributeCategoryQuery.getLimit());
        IPage<ProductAttributeCategory> data = productAttributeCategoryMapper.selectPage(page, queryWrapper);
        List<ProductAttributeCategory> productAttributeCategoryList = data.getRecords();
        List<ProductAttributeCategoryListVo> productAttributeCategoryListVoList = new ArrayList<>();
        if (!productAttributeCategoryList.isEmpty()) {
            productAttributeCategoryList.forEach(item -> {
                ProductAttributeCategoryListVo productAttributeCategoryListVo = new ProductAttributeCategoryListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, productAttributeCategoryListVo);
                // 分类图片地址
                if (!StringUtils.isEmpty(productAttributeCategoryListVo.getImage())) {
                    productAttributeCategoryListVo.setImageUrl(CommonUtils.getImageURL(productAttributeCategoryListVo.getImage()));
                }
                // 添加人名称
                if (productAttributeCategoryListVo.getCreateUser() != null && productAttributeCategoryListVo.getCreateUser() > 0) {
                    productAttributeCategoryListVo.setCreateUserName(AdminUtils.getName((productAttributeCategoryListVo.getCreateUser())));
                }
                // 更新人名称
                if (productAttributeCategoryListVo.getUpdateUser() != null && productAttributeCategoryListVo.getUpdateUser() > 0) {
                    productAttributeCategoryListVo.setUpdateUserName(AdminUtils.getName((productAttributeCategoryListVo.getUpdateUser())));
                }
                productAttributeCategoryListVoList.add(productAttributeCategoryListVo);
            });
        }
        return JsonResult.success("操作成功", productAttributeCategoryListVoList, data.getTotal());
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        ProductAttributeCategory entity = (ProductAttributeCategory) super.getInfo(id);
        // 分类图片解析
        if (!StringUtils.isEmpty(entity.getImage())) {
            entity.setImage(CommonUtils.getImageURL(entity.getImage()));
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
    public JsonResult edit(ProductAttributeCategory entity) {
        // 分类图片
        if (entity.getImage().contains(CommonConfig.imageURL)) {
            entity.setImage(entity.getImage().replaceAll(CommonConfig.imageURL, ""));
        }
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
        ProductAttributeCategory entity = this.getById(id);
        if (entity == null) {
            return JsonResult.error("记录不存在");
        }
        return super.delete(entity);
    }

}