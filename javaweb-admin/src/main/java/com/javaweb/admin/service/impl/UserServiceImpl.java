package com.javaweb.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.config.UploadFileConfig;
import com.javaweb.common.utils.QRCodeUtil;
import com.javaweb.shiro.common.BaseServiceImpl;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.admin.constant.UserConstant;
import com.javaweb.admin.entity.User;
import com.javaweb.admin.mapper.UserMapper;
import com.javaweb.admin.query.UserQuery;
import com.javaweb.admin.service.IUserService;
import com.javaweb.system.service.ICityService;
import com.javaweb.system.utils.AdminUtils;
import com.javaweb.admin.vo.UserListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 会员用户 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-04
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ICityService cityService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        UserQuery userQuery = (UserQuery) query;
        // 查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 手机号
        if (!StringUtils.isEmpty(userQuery.getMobile())) {
            queryWrapper.like("mobile", userQuery.getMobile());
        }
        // 设备类型：1苹果 2安卓 3WAP站 4PC站 5微信小程序 6后台添加
        if (userQuery.getDevice() != null && userQuery.getDevice() > 0) {
            queryWrapper.eq("device", userQuery.getDevice());
        }

        // 用户来源：1注册会员 2马甲会员
        if (userQuery.getSource() != null && userQuery.getSource() > 0) {
            queryWrapper.eq("source", userQuery.getSource());
        }
        // 是否启用：1启用  2停用
        if (userQuery.getStatus() != null && userQuery.getStatus() > 0) {
            queryWrapper.eq("status", userQuery.getStatus());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<User> page = new Page<>(userQuery.getPageIndex(), userQuery.getPageSize());
        IPage<User> data = userMapper.selectPage(page, queryWrapper);
        List<User> userList = data.getRecords();
        List<UserListVo> userListVoList = new ArrayList<>();
        if (!userList.isEmpty()) {
            userList.forEach(item -> {
                UserListVo userListVo = new UserListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, userListVo);
                // 性别描述
                if (userListVo.getGender() != null && userListVo.getGender() > 0) {
                    userListVo.setGenderName(UserConstant.USER_GENDER_LIST.get(userListVo.getGender()));
                }
                // 用户头像地址
                if (!StringUtils.isEmpty(userListVo.getAvatar())) {
                    userListVo.setAvatarUrl(CommonUtils.getImageURL(userListVo.getAvatar()));
                }
                // 推广二维码地址
                if (!StringUtils.isEmpty(userListVo.getQrcode())) {
                    userListVo.setQrcodeUrl(CommonUtils.getImageURL(userListVo.getQrcode()));
                }
                // 设备类型描述
                if (userListVo.getDevice() != null && userListVo.getDevice() > 0) {
                    userListVo.setDeviceName(UserConstant.USER_DEVICE_LIST.get(userListVo.getDevice()));
                }
                // 用户状态描述
                if (userListVo.getLoginStatus() != null && userListVo.getLoginStatus() > 0) {
                    userListVo.setLoginStatusName(UserConstant.USER_LOGINSTATUS_LIST.get(userListVo.getLoginStatus()));
                }
                // 用户来源描述
                if (userListVo.getSource() != null && userListVo.getSource() > 0) {
                    userListVo.setSourceName(UserConstant.USER_SOURCE_LIST.get(userListVo.getSource()));
                }
                // 是否启用描述
                if (userListVo.getStatus() != null && userListVo.getStatus() > 0) {
                    userListVo.setStatusName(UserConstant.USER_STATUS_LIST.get(userListVo.getStatus()));
                }
                // 添加人名称
                if (userListVo.getCreateUser() != null && userListVo.getCreateUser() > 0) {
                    userListVo.setCreateUserName(AdminUtils.getName((userListVo.getCreateUser())));
                }
                // 修改人名称
                if (userListVo.getUpdateUser() != null && userListVo.getUpdateUser() > 0) {
                    userListVo.setUpdateUserName(AdminUtils.getName((userListVo.getUpdateUser())));
                }
                userListVoList.add(userListVo);
            });
        }
        return JsonResult.success("操作成功", userListVoList, data.getTotal());
    }

    /**
     * 获取记录详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        User entity = (User) super.getInfo(id);
        // 用户头像解析
        if (!StringUtils.isEmpty(entity.getAvatar())) {
            entity.setAvatar(CommonUtils.getImageURL(entity.getAvatar()));
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
    public JsonResult edit(User entity) {
        // 用户头像
        if (entity.getAvatar().contains(CommonConfig.imageURL)) {
            entity.setAvatar(entity.getAvatar().replaceAll(CommonConfig.imageURL, ""));
        }
        // 设置密码
        if (!StringUtils.isEmpty(entity.getPassword())) {
            entity.setPassword(CommonUtils.password(entity.getPassword()));
        }
        // 获取城市名称
        if (entity.getDistrictId() != null && entity.getDistrictId() > 0) {
            String cityArea = cityService.getCityNameByCityId(entity.getDistrictId(), " ");
            entity.setCityArea(cityArea);
        }
        // 马甲会员
        entity.setSource(2);
        // 设备类型
        entity.setDevice(6);
        if (entity.getId() == null || entity.getId() == 0 || StringUtils.isEmpty(entity.getCode())) {

            // 推广码
            String code = CommonUtils.getRandomStr(false, 20);
            entity.setCode(code);
            // 生成二维码
            try {
                // 创建二级目录(格式：年月日)
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String ymd = sdf.format(new Date());
                String uploadPath = UploadFileConfig.uploadFolder;
                String filePath = "images/qrcode/" + ymd + "/";
                File dirFile = new File(uploadPath + filePath);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
                // 图片文件路径
                String imagePath = String.format("%s%s.jpg", ymd, CommonUtils.getRandomStr(true, 8));
                String destImagePath = uploadPath + filePath + imagePath;
                QRCodeUtil.encodeQrCode(code, 500, 500, destImagePath);
                entity.setQrcode(filePath + imagePath);
            } catch (Exception e) {

            }
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
        User entity = this.getById(id);
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
    public JsonResult setStatus(User entity) {
        if (entity.getId() == null || entity.getId() <= 0) {
            return JsonResult.error("记录ID不能为空");
        }
        if (entity.getStatus() == null) {
            return JsonResult.error("记录状态不能为空");
        }
        return super.setStatus(entity);
    }
}