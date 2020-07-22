package com.javaweb.generator.controller;


import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.generator.entity.GenTableColumn;
import com.javaweb.generator.query.GenTableColumnQuery;
import com.javaweb.generator.service.IGenTableColumnService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.common.BaseController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 代码生成列 控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-10
 */
@Controller
@RequestMapping("/gentablecolumn")
public class GenTableColumnController extends BaseController {

    @Autowired
    private IGenTableColumnService genTableColumnService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
//    @RequiresPermissions("sys:gentablecolumn:list")
    @ResponseBody
    @PostMapping("/list")
    public JsonResult list(GenTableColumnQuery query) {
        return genTableColumnService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
//    @RequiresPermissions("sys:gentablecolumn:add")
    @Log(title = "代码生成列", businessType = BusinessType.INSERT)
    @ResponseBody
    @PostMapping("/add")
    public JsonResult add(@RequestBody GenTableColumn entity) {
        return genTableColumnService.edit(entity);
    }

    /**
     * 修改记录
     *
     * @param entity 实体对象
     * @return
     */
//    @RequiresPermissions("sys:gentablecolumn:update")
    @Log(title = "代码生成列", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping("/update")
    public JsonResult update(@RequestBody GenTableColumn entity) {
        return genTableColumnService.edit(entity);
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
            info = genTableColumnService.info(id);
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
//    @RequiresPermissions("sys:gentablecolumn:delete")
    @Log(title = "代码生成列", businessType = BusinessType.DELETE)
    @ResponseBody
    @GetMapping("/delete/{id}")
    public JsonResult delete(@PathVariable("id") Integer id) {
        return genTableColumnService.deleteById(id);
    }

    /**
     * 批量删除
     *
     * @param ids 记录ID(多个使用逗号","分隔)
     * @return
     */
//    @RequiresPermissions("sys:gentablecolumn:batchDelete")
    @Log(title = "代码生成列", businessType = BusinessType.BATCH_DELETE)
    @ResponseBody
    @GetMapping("/batchDelete/{ids}")
    public JsonResult batchDelete(@PathVariable("ids") String ids) {
        return genTableColumnService.deleteByIds(ids);
    }

}
