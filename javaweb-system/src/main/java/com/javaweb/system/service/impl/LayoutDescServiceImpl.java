package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.shiro.common.BaseServiceImpl;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.entity.Item;
import com.javaweb.system.entity.LayoutDesc;
import com.javaweb.system.mapper.ItemMapper;
import com.javaweb.system.mapper.LayoutDescMapper;
import com.javaweb.system.query.LayoutDescQuery;
import com.javaweb.system.service.ILayoutDescService;
import com.javaweb.system.utils.AdminUtils;
import com.javaweb.system.vo.LayoutDescListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 布局描述 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
@Service
public class LayoutDescServiceImpl extends BaseServiceImpl<LayoutDescMapper, LayoutDesc> implements ILayoutDescService {

    @Autowired
    private LayoutDescMapper layoutDescMapper;
    @Autowired
    private ItemMapper itemMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        LayoutDescQuery layoutDescQuery = (LayoutDescQuery) query;
        // 查询条件
        QueryWrapper<LayoutDesc> queryWrapper = new QueryWrapper<>();
        // 推荐位名称
        if (!StringUtils.isEmpty(layoutDescQuery.getName())) {
            queryWrapper.like("name", layoutDescQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<LayoutDesc> page = new Page<>(layoutDescQuery.getPage(), layoutDescQuery.getLimit());
        IPage<LayoutDesc> data = layoutDescMapper.selectPage(page, queryWrapper);
        List<LayoutDesc> layoutDescList = data.getRecords();
        List<LayoutDescListVo> layoutDescListVoList = new ArrayList<>();
        if (!layoutDescList.isEmpty()) {
            layoutDescList.forEach(item -> {
                LayoutDescListVo layoutDescListVo = new LayoutDescListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, layoutDescListVo);
                // 所属站点
                if (layoutDescListVo.getItemId() != null && layoutDescListVo.getItemId() > 0) {
                    Item itemInfo = itemMapper.selectById(layoutDescListVo.getItemId());
                    if (itemInfo != null) {
                        layoutDescListVo.setItemName(itemInfo.getName());
                    }
                }
                // 添加人名称
                if (layoutDescListVo.getCreateUser() > 0) {
                    layoutDescListVo.setCreateUserName(AdminUtils.getName((layoutDescListVo.getCreateUser())));
                }
                // 更新人名称
                if (layoutDescListVo.getUpdateUser() > 0) {
                    layoutDescListVo.setUpdateUserName(AdminUtils.getName((layoutDescListVo.getUpdateUser())));
                }
                layoutDescListVoList.add(layoutDescListVo);
            });
        }
        return JsonResult.success("操作成功", layoutDescListVoList, data.getTotal());
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        LayoutDesc entity = (LayoutDesc) super.getInfo(id);
        return entity;
    }

    /**
     * 添加或编辑记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(LayoutDesc entity) {
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
        LayoutDesc entity = this.getById(id);
        if (entity == null) {
            return JsonResult.error("记录不存在");
        }
        return super.delete(entity);
    }

    /**
     * 根据ID获取页面位置描述
     * @param itemId 页面位置ID
     * @param locId 位置ID
     * @return
     */
    @Override
    public LayoutDesc getLocDescById(Integer itemId, Integer locId) {
        // 查询条件
        QueryWrapper<LayoutDesc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("item_id", itemId);
        queryWrapper.eq("loc_id", locId);
        queryWrapper.eq("mark", 1);
        LayoutDesc layoutDescInfo = layoutDescMapper.selectOne(queryWrapper);
        return layoutDescInfo;
    }
}