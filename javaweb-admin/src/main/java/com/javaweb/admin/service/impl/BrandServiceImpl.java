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
import com.javaweb.admin.constant.BrandConstant;
import com.javaweb.admin.entity.Brand;
import com.javaweb.admin.mapper.BrandMapper;
import com.javaweb.admin.query.BrandQuery;
import com.javaweb.admin.service.IBrandService;
import com.javaweb.system.utils.AdminUtils;
import com.javaweb.admin.vo.BrandListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品品牌 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-05
 */
@Service
public class BrandServiceImpl extends BaseServiceImpl<BrandMapper, Brand> implements IBrandService {

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
        BrandQuery brandQuery = (BrandQuery) query;
        // 查询条件
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        // 状态：1启用 2停用
        if (brandQuery.getStatus() != null && brandQuery.getStatus() > 0) {
            queryWrapper.eq("status", brandQuery.getStatus());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<Brand> page = new Page<>(brandQuery.getPage(), brandQuery.getLimit());
        IPage<Brand> data = brandMapper.selectPage(page, queryWrapper);
        List<Brand> brandList = data.getRecords();
        List<BrandListVo> brandListVoList = new ArrayList<>();
        if (!brandList.isEmpty()) {
            brandList.forEach(item -> {
                BrandListVo brandListVo = new BrandListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, brandListVo);
                // 品牌logo地址
                if (!StringUtils.isEmpty(brandListVo.getBrandLogo())) {
                    brandListVo.setBrandLogoUrl(CommonUtils.getImageURL(brandListVo.getBrandLogo()));
                }
                // 专区大图地址
                if (!StringUtils.isEmpty(brandListVo.getBigPic())) {
                    brandListVo.setBigPicUrl(CommonUtils.getImageURL(brandListVo.getBigPic()));
                }
                // 状态描述
                if (brandListVo.getStatus() != null && brandListVo.getStatus() > 0) {
                    brandListVo.setStatusName(BrandConstant.BRAND_STATUS_LIST.get(brandListVo.getStatus()));
                }
                // 添加人名称
                if (brandListVo.getCreateUser() != null && brandListVo.getCreateUser() > 0) {
                    brandListVo.setCreateUserName(AdminUtils.getName((brandListVo.getCreateUser())));
                }
                // 更新人名称
                if (brandListVo.getUpdateUser() != null && brandListVo.getUpdateUser() > 0) {
                    brandListVo.setUpdateUserName(AdminUtils.getName((brandListVo.getUpdateUser())));
                }
                brandListVoList.add(brandListVo);
            });
        }
        return JsonResult.success("操作成功", brandListVoList, data.getTotal());
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        Brand entity = (Brand) super.getInfo(id);
        // 品牌logo解析
        if (!StringUtils.isEmpty(entity.getBrandLogo())) {
            entity.setBrandLogo(CommonUtils.getImageURL(entity.getBrandLogo()));
        }
        // 专区大图解析
        if (!StringUtils.isEmpty(entity.getBigPic())) {
            entity.setBigPic(CommonUtils.getImageURL(entity.getBigPic()));
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
    public JsonResult edit(Brand entity) {
        // 品牌logo
        if (entity.getBrandLogo().contains(CommonConfig.imageURL)) {
            entity.setBrandLogo(entity.getBrandLogo().replaceAll(CommonConfig.imageURL, ""));
        }
        // 专区大图
        if (entity.getBigPic().contains(CommonConfig.imageURL)) {
            entity.setBigPic(entity.getBigPic().replaceAll(CommonConfig.imageURL, ""));
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
        Brand entity = this.getById(id);
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
    public JsonResult setStatus(Brand entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("记录ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("记录状态不能为空");
        }
        return super.setStatus(entity);
    }
}