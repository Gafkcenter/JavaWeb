package com.javaweb.admin.controller;


import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.admin.entity.UserAddress;
import com.javaweb.admin.query.UserAddressQuery;
import com.javaweb.admin.service.IUserAddressService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaweb.common.common.BaseController;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 会员地址 控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-04
 */
@Controller
@RequestMapping("/useraddress")
public class UserAddressController extends BaseController {

    @Autowired
    private IUserAddressService userAddressService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
//    @RequiresPermissions("sys:useraddress:list")
    @ResponseBody
    @PostMapping("/list")
    public JsonResult list(UserAddressQuery query) {
        return userAddressService.getList(query);
    }

    /**
     * 删除记录
     *
     * @param id 记录ID
     * @return
     */
//    @RequiresPermissions("sys:useraddress:delete")
    @Log(title = "会员地址", businessType = BusinessType.DELETE)
    @Override
    public JsonResult delete(Integer id) {
        return userAddressService.deleteById(id);
    }

}
