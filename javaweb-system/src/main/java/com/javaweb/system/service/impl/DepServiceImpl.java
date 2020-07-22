package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.constant.DepConstant;
import com.javaweb.system.entity.Dep;
import com.javaweb.system.mapper.DepMapper;
import com.javaweb.system.query.DepQuery;
import com.javaweb.system.service.IDepService;
import com.javaweb.system.utils.AdminUtils;
import com.javaweb.system.vo.DepListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-03
 */
@Service
public class DepServiceImpl extends BaseServiceImpl<DepMapper, Dep> implements IDepService {

    @Autowired
    private DepMapper depMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        DepQuery depQuery = (DepQuery) query;
        // 查询条件
        QueryWrapper<Dep> queryWrapper = new QueryWrapper<>();
        // 部门名称
        if (!StringUtils.isEmpty(depQuery.getName())) {
            queryWrapper.like("name", depQuery.getName());
        }
        // 类型：1公司 2部门
        if (depQuery.getType() != null) {
            queryWrapper.eq("type", depQuery.getType());
        }
        // 是否有子级：1是 2否
        if (depQuery.getHasChild() != null) {
            queryWrapper.eq("has_child", depQuery.getHasChild());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<Dep> page = new Page<>(depQuery.getPage(), depQuery.getLimit());
        IPage<Dep> data = depMapper.selectPage(page, queryWrapper);
        List<Dep> depList = data.getRecords();
        List<DepListVo> depListVoList = new ArrayList<>();
        if (!depList.isEmpty()) {
            depList.forEach(item -> {
                DepListVo depListVo = new DepListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, depListVo);
                // 类型描述
                if (depListVo.getType() != null && depListVo.getType() > 0) {
                    depListVo.setTypeName(DepConstant.DEP_TYPE_LIST.get(depListVo.getType()));
                }
                // 是否有子级描述
                if (depListVo.getHasChild() != null && depListVo.getHasChild() > 0) {
                    depListVo.setHasChildName(DepConstant.DEP_HASCHILD_LIST.get(depListVo.getHasChild()));
                }
                // 添加人名称
                if (depListVo.getCreateUser() > 0) {
                    depListVo.setCreateUserName(AdminUtils.getName((depListVo.getCreateUser())));
                }
                // 更新人名称
                if (depListVo.getUpdateUser() > 0) {
                    depListVo.setUpdateUserName(AdminUtils.getName((depListVo.getUpdateUser())));
                }
                depListVoList.add(depListVo);
            });
        }
        return JsonResult.success("操作成功", depListVoList, data.getTotal());
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        Dep entity = (Dep) super.getInfo(id);
        return entity;
    }

    /**
     * 添加或编辑记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Dep entity) {
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
        Dep entity = this.getById(id);
        if (entity == null) {
            return JsonResult.error("记录不存在");
        }
        return super.delete(entity);
    }

    /**
     * 根据部门ID获取部门名称
     * @param depId 部门ID
     * @param delimiter 拼接字符
     * @return
     */
    @Override
    public String getDepNameByDepId(Integer depId, String delimiter) {
        List<String> nameList = new ArrayList<>();
        while (depId > 0) {
            Dep depInfo = depMapper.selectById(depId);
            if (depInfo != null) {
                nameList.add(depInfo.getName());
                depId = depInfo.getPid();
            } else {
                depId = 1;
            }
        }
        // 使用集合工具实现数组翻转
        Collections.reverse(nameList);
        return org.apache.commons.lang3.StringUtils.join(nameList.toArray(), delimiter);
    }
}