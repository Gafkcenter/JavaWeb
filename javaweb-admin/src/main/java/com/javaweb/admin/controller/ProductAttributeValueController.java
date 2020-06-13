package com.javaweb.admin.controller;


import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.admin.entity.ProductAttributeValue;
import com.javaweb.admin.query.ProductAttributeValueQuery;
import com.javaweb.admin.service.IProductAttributeValueService;
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
 * 商品属性值 控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-06-09
 */
@Controller
@RequestMapping("/productattributevalue")
public class ProductAttributeValueController extends BaseController {

    @Autowired
    private IProductAttributeValueService productAttributeValueService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
//    @RequiresPermissions("sys:productattributevalue:list")
    @ResponseBody
    @PostMapping("/list")
    public JsonResult list(ProductAttributeValueQuery query) {
        return productAttributeValueService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
//    @RequiresPermissions("sys:productattributevalue:add")
    @Log(title = "商品属性值", businessType = BusinessType.INSERT)
    @ResponseBody
    @PostMapping("/add")
    public JsonResult add(@RequestBody ProductAttributeValue entity) {
        return productAttributeValueService.edit(entity);
    }

    /**
     * 修改记录
     *
     * @param entity 实体对象
     * @return
     */
//    @RequiresPermissions("sys:productattributevalue:update")
    @Log(title = "商品属性值", businessType = BusinessType.UPDATE)
    @ResponseBody
    @PostMapping("/update")
    public JsonResult update(@RequestBody ProductAttributeValue entity) {
        return productAttributeValueService.edit(entity);
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
            info = productAttributeValueService.info(id);
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
//    @RequiresPermissions("sys:productattributevalue:delete")
    @Log(title = "商品属性值", businessType = BusinessType.DELETE)
    @Override
    public JsonResult delete(Integer id) {
        return productAttributeValueService.deleteById(id);
    }

}
