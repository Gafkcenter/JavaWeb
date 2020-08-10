package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.constant.LevelConstant;
import com.javaweb.system.dto.LevelDto;
import com.javaweb.system.entity.Level;
import com.javaweb.system.mapper.LevelMapper;
import com.javaweb.system.query.LevelQuery;
import com.javaweb.system.service.ILevelService;
import com.javaweb.system.utils.AdminUtils;
import com.javaweb.system.vo.LevelListVo;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 职级 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
@Service
public class LevelServiceImpl extends BaseServiceImpl<LevelMapper, Level> implements ILevelService {

    @Autowired
    private LevelMapper levelMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        LevelQuery levelQuery = (LevelQuery) query;
        // 查询条件
        QueryWrapper<Level> queryWrapper = new QueryWrapper<>();
        // 职级名称
        if (!StringUtils.isEmpty(levelQuery.getName())) {
            queryWrapper.like("name", levelQuery.getName());
        }
        // 状态：1正常 2停用
        if (levelQuery.getStatus() != null) {
            queryWrapper.eq("status", levelQuery.getStatus());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<Level> page = new Page<>(levelQuery.getPage(), levelQuery.getLimit());
        IPage<Level> data = levelMapper.selectPage(page, queryWrapper);
        List<Level> levelList = data.getRecords();
        List<LevelListVo> levelListVoList = new ArrayList<>();
        if (!levelList.isEmpty()) {
            levelList.forEach(item -> {
                LevelListVo levelListVo = new LevelListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, levelListVo);
                // 状态描述
                if (levelListVo.getStatus() != null && levelListVo.getStatus() > 0) {
                    levelListVo.setStatusName(LevelConstant.LEVEL_STATUS_LIST.get(levelListVo.getStatus()));
                }
                // 添加人名称
                if (levelListVo.getCreateUser() > 0) {
                    levelListVo.setCreateUserName(AdminUtils.getName((levelListVo.getCreateUser())));
                }
                // 更新人名称
                if (levelListVo.getUpdateUser() > 0) {
                    levelListVo.setUpdateUserName(AdminUtils.getName((levelListVo.getUpdateUser())));
                }
                levelListVoList.add(levelListVo);
            });
        }
        return JsonResult.success("操作成功", levelListVoList, data.getTotal());
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        Level entity = (Level) super.getInfo(id);
        return entity;
    }

    /**
     * 添加或编辑记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Level entity) {
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
        Level entity = this.getById(id);
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
    public JsonResult setStatus(Level entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("记录ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("记录状态不能为空");
        }
        return super.setStatus(entity);
    }

    /**
     * 批量设置状态
     *
     * @param levelDto 状态Dto
     * @return
     */
    @Override
    public JsonResult batchStatus(LevelDto levelDto) {
        if (StringUtils.isEmpty(levelDto.getIds())) {
            return JsonResult.error("请选择数据源");
        }
        if (levelDto.getStatus() == null || levelDto.getStatus() <= 0) {
            return JsonResult.error("状态值不能为空");
        }
        String[] item = levelDto.getIds().split(",");
        Integer totalNum = 0;
        for (String s : item) {
            Level entity = new Level();
            entity.setId(Integer.valueOf(s));
            entity.setStatus(levelDto.getStatus());
            Integer result = levelMapper.updateById(entity);
            if (result > 0) {
                totalNum++;
            }
        }
        if (totalNum != item.length) {
            return JsonResult.error("批量设置状态失败");
        }
        return JsonResult.success("批量设置成功");
    }
}