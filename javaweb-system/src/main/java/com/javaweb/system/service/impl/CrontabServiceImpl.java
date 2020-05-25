package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.shiro.common.BaseServiceImpl;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.constant.CrontabConstant;
import com.javaweb.system.entity.Crontab;
import com.javaweb.system.mapper.CrontabMapper;
import com.javaweb.system.query.CrontabQuery;
import com.javaweb.system.service.ICrontabService;
import com.javaweb.system.utils.AdminUtils;
import com.javaweb.system.vo.CrontabListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 定时任务 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
@Service
public class CrontabServiceImpl extends BaseServiceImpl<CrontabMapper, Crontab> implements ICrontabService {

    @Autowired
    private CrontabMapper crontabMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        CrontabQuery crontabQuery = (CrontabQuery) query;
        // 查询条件
        QueryWrapper<Crontab> queryWrapper = new QueryWrapper<>();
        // 任务标题
        if (!StringUtils.isEmpty(crontabQuery.getTitle())) {
            queryWrapper.like("title", crontabQuery.getTitle());
        }
        // 状态：1正常 2暂停
        if (crontabQuery.getStatus() != null) {
            queryWrapper.eq("status", crontabQuery.getStatus());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<Crontab> page = new Page<>(crontabQuery.getPage(), crontabQuery.getLimit());
        IPage<Crontab> data = crontabMapper.selectPage(page, queryWrapper);
        List<Crontab> crontabList = data.getRecords();
        List<CrontabListVo> crontabListVoList = new ArrayList<>();
        if (!crontabList.isEmpty()) {
            crontabList.forEach(item -> {
                CrontabListVo crontabListVo = new CrontabListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, crontabListVo);
                // 状态描述
                if (crontabListVo.getStatus() != null && crontabListVo.getStatus() > 0) {
                    crontabListVo.setStatusName(CrontabConstant.CRONTAB_STATUS_LIST.get(crontabListVo.getStatus()));
                }
                // 添加人名称
                if (crontabListVo.getCreateUser() > 0) {
                    crontabListVo.setCreateUserName(AdminUtils.getName((crontabListVo.getCreateUser())));
                }
                // 更新人名称
                if (crontabListVo.getUpdateUser() > 0) {
                    crontabListVo.setUpdateUserName(AdminUtils.getName((crontabListVo.getUpdateUser())));
                }
                crontabListVoList.add(crontabListVo);
            });
        }
        return JsonResult.success("操作成功", crontabListVoList, data.getTotal());
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        Crontab entity = (Crontab) super.getInfo(id);
        return entity;
    }

    /**
     * 添加或编辑记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Crontab entity) {
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
        Crontab entity = this.getById(id);
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
    public JsonResult setStatus(Crontab entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("记录ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("记录状态不能为空");
        }
        return super.setStatus(entity);
    }
}