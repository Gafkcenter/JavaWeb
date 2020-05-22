package com.javaweb.system.controller;


import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.system.entity.OperLog;
import com.javaweb.system.query.OperLogQuery;
import com.javaweb.system.service.IOperLogService;
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
 * 操作日志 控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-04
 */
@Controller
@RequestMapping("/operlog")
public class OperLogController extends BaseController {

    @Autowired
    private IOperLogService operLogService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
//    @RequiresPermissions("sys:operlog:list")
    @ResponseBody
    @PostMapping("/list")
    public JsonResult list(OperLogQuery query) {
        return operLogService.getList(query);
    }

    /**
     * 删除记录
     *
     * @param id 记录ID
     * @return
     */
//    @RequiresPermissions("sys:operlog:delete")
    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @Override
    public JsonResult delete(Integer id) {
        return operLogService.deleteById(id);
    }

}
