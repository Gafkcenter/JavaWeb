package com.javaweb.system.controller;


import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.system.entity.DicType;
import com.javaweb.system.query.DicTypeQuery;
import com.javaweb.system.service.IDicTypeService;
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
 * 字典类型 控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
@Controller
@RequestMapping("/dictype")
public class DicTypeController extends BaseController {

    @Autowired
    private IDicTypeService dicTypeService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
//    @RequiresPermissions("sys:dictype:list")
    @ResponseBody
    @PostMapping("/list")
    public JsonResult list(DicTypeQuery query) {
        return dicTypeService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
//    @RequiresPermissions("sys:dictype:add")
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @ResponseBody
    @PostMapping("/add")
    public JsonResult add(@RequestBody DicType entity) {
        return dicTypeService.edit(entity);
    }

    /**
     * 修改记录
     *
     * @param entity 实体对象
     * @return
     */
//    @RequiresPermissions("sys:dictype:edit")
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping("/update")
    public JsonResult update(@RequestBody DicType entity) {
        return dicTypeService.edit(entity);
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
            info = dicTypeService.info(id);
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
//    @RequiresPermissions("sys:dictype:delete")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @Override
    public JsonResult delete(Integer id) {
        return dicTypeService.deleteById(id);
    }

}
