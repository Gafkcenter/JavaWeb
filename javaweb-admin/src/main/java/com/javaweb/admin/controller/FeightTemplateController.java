package com.javaweb.admin.controller;


import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.admin.entity.FeightTemplate;
import com.javaweb.admin.query.FeightTemplateQuery;
import com.javaweb.admin.service.IFeightTemplateService;
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
 * 运费模版 控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-09
 */
@Controller
@RequestMapping("/feighttemplate")
public class FeightTemplateController extends BaseController {

    @Autowired
    private IFeightTemplateService feightTemplateService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
//    @RequiresPermissions("sys:feighttemplate:list")
    @ResponseBody
    @PostMapping("/list")
    public JsonResult list(FeightTemplateQuery query) {
        return feightTemplateService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
//    @RequiresPermissions("sys:feighttemplate:add")
    @Log(title = "运费模版", businessType = BusinessType.INSERT)
    @ResponseBody
    @PostMapping("/add")
    public JsonResult add(@RequestBody FeightTemplate entity) {
        return feightTemplateService.edit(entity);
    }

    /**
     * 修改记录
     *
     * @param entity 实体对象
     * @return
     */
//    @RequiresPermissions("sys:feighttemplate:update")
    @Log(title = "运费模版", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping("/update")
    public JsonResult update(@RequestBody FeightTemplate entity) {
        return feightTemplateService.edit(entity);
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
            info = feightTemplateService.info(id);
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
//    @RequiresPermissions("sys:feighttemplate:delete")
    @Log(title = "运费模版", businessType = BusinessType.DELETE)
    @Override
    public JsonResult delete(Integer id) {
        return feightTemplateService.deleteById(id);
    }

}
