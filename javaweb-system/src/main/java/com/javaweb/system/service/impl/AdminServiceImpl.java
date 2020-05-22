package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.shiro.common.BaseServiceImpl;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.constant.AdminConstant;
import com.javaweb.system.entity.*;
import com.javaweb.system.mapper.AdminMapper;
import com.javaweb.system.mapper.LevelMapper;
import com.javaweb.system.mapper.PositionMapper;
import com.javaweb.system.query.AdminQuery;
import com.javaweb.system.service.IAdminService;
import com.javaweb.system.service.ICityService;
import com.javaweb.system.service.IDepService;
import com.javaweb.system.utils.AdminUtils;
import com.javaweb.system.vo.AdminInfoVo;
import com.javaweb.system.vo.AdminListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统人员 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private LevelMapper levelMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private ICityService cityService;
    @Autowired
    private IDepService depService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        AdminQuery adminQuery = (AdminQuery) query;
        // 查询条件
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        // 人员姓名/手机号
        if (!StringUtils.isEmpty(adminQuery.getKeywords())) {
            queryWrapper.like("realname", adminQuery.getKeywords()).or().like("mobile", adminQuery.getKeywords());
        }
        // 性别:1男 2女 3保密
        if (adminQuery.getGender() != null) {
            queryWrapper.eq("gender", adminQuery.getGender());
        }
        // 状态：1正常 2禁用
        if (adminQuery.getStatus() != null) {
            queryWrapper.eq("status", adminQuery.getStatus());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<Admin> page = new Page<>(adminQuery.getPageIndex(), adminQuery.getPageSize());
        IPage<Admin> data = adminMapper.selectPage(page, queryWrapper);
        List<Admin> adminList = data.getRecords();
        List<AdminListVo> adminListVoList = new ArrayList<>();
        if (!adminList.isEmpty()) {
            adminList.forEach(item -> {
                AdminListVo adminListVo = new AdminListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, adminListVo);
                // 性别描述
                if (adminListVo.getGender() != null && adminListVo.getGender() > 0) {
                    adminListVo.setGenderName(AdminConstant.ADMIN_GENDER_LIST.get(adminListVo.getGender()));
                }
                // 头像地址
                if (!StringUtils.isEmpty(adminListVo.getAvatar())) {
                    adminListVo.setAvatarUrl(CommonUtils.getImageURL(adminListVo.getAvatar()));
                }
                // 状态描述
                if (adminListVo.getStatus() != null && adminListVo.getStatus() > 0) {
                    adminListVo.setStatusName(AdminConstant.ADMIN_STATUS_LIST.get(adminListVo.getStatus()));
                }
                // 所属部门
                if (adminListVo.getDeptId() != null && adminListVo.getDeptId() > 0) {
                    String deptName = depService.getDepNameByDepId(adminListVo.getDeptId(), ">>");
                    adminListVo.setDeptName(deptName);
                }
                // 获取职级
                Level levelInfo = levelMapper.selectById(item.getLevelId());
                if (levelInfo != null) {
                    adminListVo.setLevelName(levelInfo.getName());
                }
                // 获取岗位
                Position positionInfo = positionMapper.selectById(item.getPositionId());
                if (positionInfo != null) {
                    adminListVo.setPositionName(positionInfo.getName());
                }
                // 获取所属城市名称
                String cityName = cityService.getCityNameByCityId(adminListVo.getDistrictId(), ">>");
                adminListVo.setCityName(cityName);
                // 添加人名称
                if (adminListVo.getCreateUser() > 0) {
                    adminListVo.setCreateUserName(AdminUtils.getName((adminListVo.getCreateUser())));
                }
                // 更新人名称
                if (adminListVo.getUpdateUser() > 0) {
                    adminListVo.setUpdateUserName(AdminUtils.getName((adminListVo.getUpdateUser())));
                }
                adminListVoList.add(adminListVo);
            });
        }
        return JsonResult.success("操作成功", adminListVoList, data.getTotal());
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        Admin entity = (Admin) super.getInfo(id);
        // 拷贝属性
        AdminInfoVo adminInfoVo = new AdminInfoVo();
        BeanUtils.copyProperties(entity, adminInfoVo);
        // 头像解析
        if (!StringUtils.isEmpty(entity.getAvatar())) {
            adminInfoVo.setAvatar(CommonUtils.getImageURL(entity.getAvatar()));
        }
        // 所属城市
        if (entity.getDistrictId() != null && entity.getDistrictId() > 0) {
            String cityName = cityService.getCityNameByCityId(entity.getDistrictId(), " ");
            adminInfoVo.setCityName(cityName);
        }
        // 获取部门
        if (adminInfoVo.getDeptId() != null && adminInfoVo.getDeptId() > 0) {
            String deptName = depService.getDepNameByDepId(adminInfoVo.getDeptId(), " -> ");
            adminInfoVo.setDeptName(deptName);
        }
        return adminInfoVo;
    }

    /**
     * 添加或编辑记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Admin entity) {
        // 头像
        if (entity.getAvatar().contains(CommonConfig.imageURL)) {
            entity.setAvatar(entity.getAvatar().replaceAll(CommonConfig.imageURL, ""));
        }
        if (!StringUtils.isEmpty(entity.getPassword())) {
            entity.setPassword(CommonUtils.password(entity.getPassword()));
        } else {
            entity.setPassword(null);
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
        Admin entity = this.getById(id);
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
    public JsonResult setStatus(Admin entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("记录ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("记录状态不能为空");
        }
        return super.setStatus(entity);
    }
}