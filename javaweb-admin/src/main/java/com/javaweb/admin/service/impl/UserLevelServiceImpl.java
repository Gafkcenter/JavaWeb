package com.javaweb.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.shiro.common.BaseServiceImpl;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.admin.entity.UserLevel;
import com.javaweb.admin.mapper.UserLevelMapper;
import com.javaweb.admin.query.UserLevelQuery;
import com.javaweb.admin.service.IUserLevelService;
import com.javaweb.system.utils.AdminUtils;
import com.javaweb.admin.vo.UserLevelListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 会员等级 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-04
 */
@Service
public class UserLevelServiceImpl extends BaseServiceImpl<UserLevelMapper, UserLevel> implements IUserLevelService {

    @Autowired
    private UserLevelMapper userLevelMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        UserLevelQuery userLevelQuery = (UserLevelQuery) query;
        // 查询条件
        QueryWrapper<UserLevel> queryWrapper = new QueryWrapper<>();
        // 级别名称
        if (!StringUtils.isEmpty(userLevelQuery.getName())) {
            queryWrapper.like("name", userLevelQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<UserLevel> page = new Page<>(userLevelQuery.getPageIndex(), userLevelQuery.getPageSize());
        IPage<UserLevel> data = userLevelMapper.selectPage(page, queryWrapper);
        List<UserLevel> userLevelList = data.getRecords();
        List<UserLevelListVo> userLevelListVoList = new ArrayList<>();
        if (!userLevelList.isEmpty()) {
            userLevelList.forEach(item -> {
                UserLevelListVo userLevelListVo = new UserLevelListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, userLevelListVo);
                // 添加人名称
                if (userLevelListVo.getCreateUser() > 0) {
                    userLevelListVo.setCreateUserName(AdminUtils.getName((userLevelListVo.getCreateUser())));
                }
                // 更新人名称
                if (userLevelListVo.getUpdateUser() > 0) {
                    userLevelListVo.setUpdateUserName(AdminUtils.getName((userLevelListVo.getUpdateUser())));
                }
                userLevelListVoList.add(userLevelListVo);
            });
        }
        return JsonResult.success("操作成功", userLevelListVoList, data.getTotal());
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        UserLevel entity = (UserLevel) super.getInfo(id);
        return entity;
    }

    /**
     * 添加或编辑记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(UserLevel entity) {
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
        UserLevel entity = this.getById(id);
        if (entity == null) {
            return JsonResult.error("记录不存在");
        }
        return super.delete(entity);
    }

}