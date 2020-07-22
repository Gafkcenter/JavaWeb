package com.javaweb.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.common.utils.ServletUtils;
import com.javaweb.system.dto.ConfigSettingDto;
import com.javaweb.system.entity.Config;
import com.javaweb.system.entity.ConfigGroup;
import com.javaweb.system.query.ConfigQuery;
import com.javaweb.system.service.IConfigGroupService;
import com.javaweb.system.service.IConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统配置 控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-03
 */
@Controller
@RequestMapping("/config")
public class ConfigController extends BaseController {

    @Autowired
    private IConfigService configService;
    @Autowired
    private IConfigGroupService configGroupService;

    /**
     * 配置列表
     *
     * @param model
     * @return
     */
    @Override
    public String index(Model model) {
        String tabId = ServletUtils.getParameter("tabId");
        Integer groupId = 0;
        if (tabId == null) {
            // 获取第一个配置分组
            QueryWrapper<ConfigGroup> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("mark", 1);
            queryWrapper.orderByAsc("sort");
            queryWrapper.last("limit 1");
            ConfigGroup configGroup = configGroupService.getOne(queryWrapper);
            if (configGroup != null) {
                groupId = configGroup.getId();
            }
        } else {
            groupId = Integer.valueOf(tabId);
        }
        model.addAttribute("tabId", groupId);
        return super.index(model);
    }

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
//    @RequiresPermissions("sys:config:list")
    @ResponseBody
    @PostMapping("/list")
    public JsonResult list(ConfigQuery query) {
        return configService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
//    @RequiresPermissions("sys:config:add")
    @Log(title = "系统配置", businessType = BusinessType.INSERT)
    @ResponseBody
    @PostMapping("/add")
    public JsonResult add(@RequestBody Config entity) {
        return configService.edit(entity);
    }

    /**
     * 修改记录
     *
     * @param entity 实体对象
     * @return
     */
//    @RequiresPermissions("sys:config:update")
    @Log(title = "系统配置", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping("/update")
    public JsonResult update(@RequestBody Config entity) {
        return configService.edit(entity);
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
            info = configService.info(id);
        } else {
            String groupId = ServletUtils.getParameter("groupId");
            info.put("groupId", groupId);
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
//    @RequiresPermissions("sys:config:delete")
    @Log(title = "系统配置", businessType = BusinessType.DELETE)
    @ResponseBody
    @GetMapping("/delete/{id}")
    public JsonResult delete(@PathVariable("id") Integer id) {
        return configService.deleteById(id);
    }

    /**
     * 批量删除
     *
     * @param ids 记录ID(多个使用逗号","分隔)
     * @return
     */
//    @RequiresPermissions("sys:config:batchDelete")
    @Log(title = "系统配置", businessType = BusinessType.BATCH_DELETE)
    @ResponseBody
    @GetMapping("/batchDelete/{ids}")
    public JsonResult batchDelete(@PathVariable("ids") String ids) {
        return configService.deleteByIds(ids);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
//    @RequiresPermissions("sys:config:status")
    @Log(title = "系统配置", businessType = BusinessType.STATUS)
    @ResponseBody
    @PostMapping("/setStatus")
    public JsonResult setStatus(@RequestBody Config entity) {
        return configService.setStatus(entity);
    }
}
