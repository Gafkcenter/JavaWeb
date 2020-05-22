package com.javaweb.system.controller;


import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.BusinessType;
import com.javaweb.system.entity.Message;
import com.javaweb.system.query.MessageQuery;
import com.javaweb.system.service.IMessageService;
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
 * 系统消息 控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-04
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

    @Autowired
    private IMessageService messageService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
//    @RequiresPermissions("sys:message:list")
    @ResponseBody
    @PostMapping("/list")
    public JsonResult list(MessageQuery query) {
        return messageService.getList(query);
    }

    /**
     * 删除记录
     *
     * @param id 记录ID
     * @return
     */
//    @RequiresPermissions("sys:message:delete")
    @Log(title = "系统消息", businessType = BusinessType.DELETE)
    @Override
    public JsonResult delete(Integer id) {
        return messageService.deleteById(id);
    }

}
