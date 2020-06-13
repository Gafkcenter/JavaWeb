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
import com.javaweb.admin.constant.FeightTemplateConstant;
import com.javaweb.admin.entity.FeightTemplate;
import com.javaweb.admin.mapper.FeightTemplateMapper;
import com.javaweb.admin.query.FeightTemplateQuery;
import com.javaweb.admin.service.IFeightTemplateService;
import com.javaweb.system.utils.AdminUtils;
import com.javaweb.admin.vo.FeightTemplateListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 运费模版 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-09
 */
@Service
public class FeightTemplateServiceImpl extends BaseServiceImpl<FeightTemplateMapper, FeightTemplate> implements IFeightTemplateService {

    @Autowired
    private FeightTemplateMapper feightTemplateMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        FeightTemplateQuery feightTemplateQuery = (FeightTemplateQuery) query;
        // 查询条件
        QueryWrapper<FeightTemplate> queryWrapper = new QueryWrapper<>();
        // 模板名称
        if (!StringUtils.isEmpty(feightTemplateQuery.getName())) {
            queryWrapper.like("name", feightTemplateQuery.getName());
        }
        // 计费类型：1按重量 2按件数
        if (feightTemplateQuery.getChargeType() != null && feightTemplateQuery.getChargeType() > 0) {
            queryWrapper.eq("charge_type", feightTemplateQuery.getChargeType());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<FeightTemplate> page = new Page<>(feightTemplateQuery.getPage(), feightTemplateQuery.getLimit());
        IPage<FeightTemplate> data = feightTemplateMapper.selectPage(page, queryWrapper);
        List<FeightTemplate> feightTemplateList = data.getRecords();
        List<FeightTemplateListVo> feightTemplateListVoList = new ArrayList<>();
        if (!feightTemplateList.isEmpty()) {
            feightTemplateList.forEach(item -> {
                FeightTemplateListVo feightTemplateListVo = new FeightTemplateListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, feightTemplateListVo);
                // 计费类型描述
                if (feightTemplateListVo.getChargeType() != null && feightTemplateListVo.getChargeType() > 0) {
                    feightTemplateListVo.setChargeTypeName(FeightTemplateConstant.FEIGHTTEMPLATE_CHARGETYPE_LIST.get(feightTemplateListVo.getChargeType()));
                }
                // 添加人名称
                if (feightTemplateListVo.getCreateUser() != null && feightTemplateListVo.getCreateUser() > 0) {
                    feightTemplateListVo.setCreateUserName(AdminUtils.getName((feightTemplateListVo.getCreateUser())));
                }
                // 更新人名称
                if (feightTemplateListVo.getUpdateUser() != null && feightTemplateListVo.getUpdateUser() > 0) {
                    feightTemplateListVo.setUpdateUserName(AdminUtils.getName((feightTemplateListVo.getUpdateUser())));
                }
                feightTemplateListVoList.add(feightTemplateListVo);
            });
        }
        return JsonResult.success("操作成功", feightTemplateListVoList, data.getTotal());
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        FeightTemplate entity = (FeightTemplate) super.getInfo(id);
        return entity;
    }

    /**
     * 添加或编辑记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(FeightTemplate entity) {
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
        FeightTemplate entity = this.getById(id);
        if (entity == null) {
            return JsonResult.error("记录不存在");
        }
        return super.delete(entity);
    }

}