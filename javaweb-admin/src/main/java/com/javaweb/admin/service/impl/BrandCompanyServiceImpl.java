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
import com.javaweb.admin.entity.BrandCompany;
import com.javaweb.admin.mapper.BrandCompanyMapper;
import com.javaweb.admin.query.BrandCompanyQuery;
import com.javaweb.admin.service.IBrandCompanyService;
import com.javaweb.system.utils.AdminUtils;
import com.javaweb.admin.vo.BrandCompanyListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 品牌商 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-05
 */
@Service
public class BrandCompanyServiceImpl extends BaseServiceImpl<BrandCompanyMapper, BrandCompany> implements IBrandCompanyService {

    @Autowired
    private BrandCompanyMapper brandCompanyMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        BrandCompanyQuery brandCompanyQuery = (BrandCompanyQuery) query;
        // 查询条件
        QueryWrapper<BrandCompany> queryWrapper = new QueryWrapper<>();
        // 品牌商名称
        if (!StringUtils.isEmpty(brandCompanyQuery.getName())) {
            queryWrapper.like("name", brandCompanyQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<BrandCompany> page = new Page<>(brandCompanyQuery.getPage(), brandCompanyQuery.getLimit());
        IPage<BrandCompany> data = brandCompanyMapper.selectPage(page, queryWrapper);
        List<BrandCompany> brandCompanyList = data.getRecords();
        List<BrandCompanyListVo> brandCompanyListVoList = new ArrayList<>();
        if (!brandCompanyList.isEmpty()) {
            brandCompanyList.forEach(item -> {
                BrandCompanyListVo brandCompanyListVo = new BrandCompanyListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, brandCompanyListVo);
                // 品牌商页的品牌商图片地址
                if (!StringUtils.isEmpty(brandCompanyListVo.getImage())) {
                    brandCompanyListVo.setImageUrl(CommonUtils.getImageURL(brandCompanyListVo.getImage()));
                }
                // 添加人名称
                if (brandCompanyListVo.getCreateUser() != null && brandCompanyListVo.getCreateUser() > 0) {
                    brandCompanyListVo.setCreateUserName(AdminUtils.getName((brandCompanyListVo.getCreateUser())));
                }
                // 更新人名称
                if (brandCompanyListVo.getUpdateUser() != null && brandCompanyListVo.getUpdateUser() > 0) {
                    brandCompanyListVo.setUpdateUserName(AdminUtils.getName((brandCompanyListVo.getUpdateUser())));
                }
                brandCompanyListVoList.add(brandCompanyListVo);
            });
        }
        return JsonResult.success("操作成功", brandCompanyListVoList, data.getTotal());
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        BrandCompany entity = (BrandCompany) super.getInfo(id);
        // 品牌商页的品牌商图片解析
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
    public JsonResult edit(BrandCompany entity) {
        // 品牌商页的品牌商图片
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
        BrandCompany entity = this.getById(id);
        if (entity == null) {
            return JsonResult.error("记录不存在");
        }
        return super.delete(entity);
    }

}