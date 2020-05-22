package com.javaweb.admin.controller;


import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.admin.entity.UserLevel;
import com.javaweb.admin.query.UserLevelQuery;
import com.javaweb.admin.service.IUserLevelService;
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
 * 会员等级 控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-04
 */
@Controller
@RequestMapping("/userlevel")
public class UserLevelController extends BaseController {

    @Autowired
    private IUserLevelService userLevelService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
//    @RequiresPermissions("sys:userlevel:list")
    @ResponseBody
    @PostMapping("/list")
    public JsonResult list(UserLevelQuery query) {
        return userLevelService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
//    @RequiresPermissions("sys:userlevel:add")
    @Log(title = "会员等级", businessType = BusinessType.INSERT)
    @ResponseBody
    @PostMapping("/add")
    public JsonResult add(@RequestBody UserLevel entity) {
        return userLevelService.edit(entity);
    }

    /**
     * 修改记录
     *
     * @param entity 实体对象
     * @return
     */
//    @RequiresPermissions("sys:userlevel:update")
    @Log(title = "会员等级", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping("/update")
    public JsonResult update(@RequestBody UserLevel entity) {
        return userLevelService.edit(entity);
    }

    /**
     * 获取记录详情
     *
     * @param id    记录ID
     * @param model 模型
     * @return
     */
    @Override
    public String edit(Integer id, Model model) {
        Map<String, Object> info = new HashMap<>();
        if (id != null && id > 0) {
            info = userLevelService.info(id);
        }
        model.addAttribute("info", info);
        return super.edit(id, model);
    }

    /**
     * 删除记录
     *
     * @param id 记录ID
     * @return
     */
//    @RequiresPermissions("sys:userlevel:delete")
    @Log(title = "会员等级", businessType = BusinessType.DELETE)
    @Override
    public JsonResult delete(Integer id) {
        return userLevelService.deleteById(id);
    }

}
