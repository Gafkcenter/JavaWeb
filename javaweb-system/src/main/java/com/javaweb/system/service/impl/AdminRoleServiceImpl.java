package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.shiro.common.BaseServiceImpl;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.entity.AdminRole;
import com.javaweb.system.mapper.AdminRoleMapper;
import com.javaweb.system.query.AdminRoleQuery;
import com.javaweb.system.service.IAdminRoleService;
import com.javaweb.system.utils.AdminUtils;
import com.javaweb.system.vo.AdminRoleListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 人员角色关系 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
@Service
public class AdminRoleServiceImpl extends BaseServiceImpl<AdminRoleMapper, AdminRole> implements IAdminRoleService {

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        AdminRoleQuery adminRoleQuery = (AdminRoleQuery) query;
        // 查询条件
        QueryWrapper<AdminRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<AdminRole> page = new Page<>(adminRoleQuery.getPage(), adminRoleQuery.getLimit());
        IPage<AdminRole> data = adminRoleMapper.selectPage(page, queryWrapper);
        List<AdminRole> adminRoleList = data.getRecords();
        List<AdminRoleListVo> adminRoleListVoList = new ArrayList<>();
        if (!adminRoleList.isEmpty()) {
            adminRoleList.forEach(item -> {
                AdminRoleListVo adminRoleListVo = new AdminRoleListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, adminRoleListVo);
                // 添加人名称
                if (adminRoleListVo.getCreateUser() > 0) {
                    adminRoleListVo.setCreateUserName(AdminUtils.getName((adminRoleListVo.getCreateUser())));
                }
                // 更新人名称
                if (adminRoleListVo.getUpdateUser() > 0) {
                    adminRoleListVo.setUpdateUserName(AdminUtils.getName((adminRoleListVo.getUpdateUser())));
                }
                adminRoleListVoList.add(adminRoleListVo);
            });
        }
        return JsonResult.success("操作成功", adminRoleListVoList, data.getTotal());
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        AdminRole entity = (AdminRole) super.getInfo(id);
        return entity;
    }

    /**
     * 添加或编辑记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(AdminRole entity) {
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
        AdminRole entity = this.getById(id);
        if (entity == null) {
            return JsonResult.error("记录不存在");
        }
        return super.delete(entity);
    }

}