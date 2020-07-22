package com.javaweb.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.admin.constant.ProductTagsConstant;
import com.javaweb.admin.entity.ProductTags;
import com.javaweb.admin.mapper.ProductTagsMapper;
import com.javaweb.admin.query.ProductTagsQuery;
import com.javaweb.admin.service.IProductTagsService;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.utils.AdminUtils;
import com.javaweb.admin.vo.ProductTagsListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品标签 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-18
 */
@Service
public class ProductTagsServiceImpl extends BaseServiceImpl<ProductTagsMapper, ProductTags> implements IProductTagsService {

    @Autowired
    private ProductTagsMapper productTagsMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        ProductTagsQuery productTagsQuery = (ProductTagsQuery) query;
        // 查询条件
        QueryWrapper<ProductTags> queryWrapper = new QueryWrapper<>();
        // 标签名
        if (!StringUtils.isEmpty(productTagsQuery.getName())) {
            queryWrapper.like("name", productTagsQuery.getName());
        }
        // 状态：1启用 2停用
        if (productTagsQuery.getStatus() != null && productTagsQuery.getStatus() > 0) {
            queryWrapper.eq("status", productTagsQuery.getStatus());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<ProductTags> page = new Page<>(productTagsQuery.getPage(), productTagsQuery.getLimit());
        IPage<ProductTags> data = productTagsMapper.selectPage(page, queryWrapper);
        List<ProductTags> productTagsList = data.getRecords();
        List<ProductTagsListVo> productTagsListVoList = new ArrayList<>();
        if (!productTagsList.isEmpty()) {
            productTagsList.forEach(item -> {
                ProductTagsListVo productTagsListVo = new ProductTagsListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, productTagsListVo);
                // 状态描述
                if (productTagsListVo.getStatus() != null && productTagsListVo.getStatus() > 0) {
                    productTagsListVo.setStatusName(ProductTagsConstant.PRODUCTTAGS_STATUS_LIST.get(productTagsListVo.getStatus()));
                }
                // 添加人名称
                if (productTagsListVo.getCreateUser() != null && productTagsListVo.getCreateUser() > 0) {
                    productTagsListVo.setCreateUserName(AdminUtils.getName((productTagsListVo.getCreateUser())));
                }
                // 更新人名称
                if (productTagsListVo.getUpdateUser() != null && productTagsListVo.getUpdateUser() > 0) {
                    productTagsListVo.setUpdateUserName(AdminUtils.getName((productTagsListVo.getUpdateUser())));
                }
                productTagsListVoList.add(productTagsListVo);
            });
        }
        return JsonResult.success("操作成功", productTagsListVoList, data.getTotal());
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        ProductTags entity = (ProductTags) super.getInfo(id);
        return entity;
    }

    /**
     * 添加或编辑记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(ProductTags entity) {
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
        ProductTags entity = this.getById(id);
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
    public JsonResult setStatus(ProductTags entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("记录ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("记录状态不能为空");
        }
        return super.setStatus(entity);
    }
}