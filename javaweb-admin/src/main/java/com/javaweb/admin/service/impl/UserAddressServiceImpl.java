package com.javaweb.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.admin.constant.UserAddressConstant;
import com.javaweb.admin.entity.UserAddress;
import com.javaweb.admin.mapper.UserAddressMapper;
import com.javaweb.admin.query.UserAddressQuery;
import com.javaweb.admin.service.IUserAddressService;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.utils.AdminUtils;
import com.javaweb.admin.vo.UserAddressListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 会员地址 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-04
 */
@Service
public class UserAddressServiceImpl extends BaseServiceImpl<UserAddressMapper, UserAddress> implements IUserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        UserAddressQuery userAddressQuery = (UserAddressQuery) query;
        // 查询条件
        QueryWrapper<UserAddress> queryWrapper = new QueryWrapper<>();
        // 收货人电话
        if (!StringUtils.isEmpty(userAddressQuery.getMobile())) {
            queryWrapper.like("mobile", userAddressQuery.getMobile());
        }
        // 默认地址：1是 2否
        if (userAddressQuery.getIsDefault() != null && userAddressQuery.getIsDefault() > 0) {
            queryWrapper.eq("is_default", userAddressQuery.getIsDefault());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<UserAddress> page = new Page<>(userAddressQuery.getPage(), userAddressQuery.getLimit());
        IPage<UserAddress> data = userAddressMapper.selectPage(page, queryWrapper);
        List<UserAddress> userAddressList = data.getRecords();
        List<UserAddressListVo> userAddressListVoList = new ArrayList<>();
        if (!userAddressList.isEmpty()) {
            userAddressList.forEach(item -> {
                UserAddressListVo userAddressListVo = new UserAddressListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, userAddressListVo);
                // 默认地址描述
                if (userAddressListVo.getIsDefault() != null && userAddressListVo.getIsDefault() > 0) {
                    userAddressListVo.setIsDefaultName(UserAddressConstant.USERADDRESS_ISDEFAULT_LIST.get(userAddressListVo.getIsDefault()));
                }
                // 创建人名称
                if (userAddressListVo.getCreateUser() != null && userAddressListVo.getCreateUser() > 0) {
                    userAddressListVo.setCreateUserName(AdminUtils.getName((userAddressListVo.getCreateUser())));
                }
                // 更新人名称
                if (userAddressListVo.getUpdateUser() != null && userAddressListVo.getUpdateUser() > 0) {
                    userAddressListVo.setUpdateUserName(AdminUtils.getName((userAddressListVo.getUpdateUser())));
                }
                userAddressListVoList.add(userAddressListVo);
            });
        }
        return JsonResult.success("操作成功", userAddressListVoList, data.getTotal());
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
        UserAddress entity = this.getById(id);
        if (entity == null) {
            return JsonResult.error("记录不存在");
        }
        return super.delete(entity);
    }

}