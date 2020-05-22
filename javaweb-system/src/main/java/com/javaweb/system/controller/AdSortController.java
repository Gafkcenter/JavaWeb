package com.javaweb.system.controller;


import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.system.entity.AdSort;
import com.javaweb.system.query.AdSortQuery;
import com.javaweb.system.service.IAdSortService;
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
 * 广告位描述 控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-01
 */
@Controller
@RequestMapping("/adsort")
public class AdSortController extends BaseController {

    @Autowired
    private IAdSortService adSortService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
//    @RequiresPermissions("sys:adsort:list")
    @ResponseBody
    @PostMapping("/list")
    public JsonResult list(AdSortQuery query) {
        return adSortService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
//    @RequiresPermissions("sys:adsort:add")
    @Log(title = "广告位描述", businessType = BusinessType.INSERT)
    @ResponseBody
    @PostMapping("/add")
    public JsonResult add(@RequestBody AdSort entity) {
        return adSortService.edit(entity);
    }

    /**
     * 修改记录
     *
     * @param entity 实体对象
     * @return
     */
//    @RequiresPermissions("sys:adsort:update")
    @Log(title = "广告位描述", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping("/update")
    public JsonResult update(@RequestBody AdSort entity) {
        return adSortService.edit(entity);
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
            info = adSortService.info(id);
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
//    @RequiresPermissions("sys:adsort:delete")
    @Log(title = "广告位描述", businessType = BusinessType.DELETE)
    @Override
    public JsonResult delete(Integer id) {
        return adSortService.deleteById(id);
    }

}
