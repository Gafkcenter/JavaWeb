package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.shiro.common.BaseServiceImpl;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.constant.SmsLogConstant;
import com.javaweb.system.entity.SmsLog;
import com.javaweb.system.mapper.SmsLogMapper;
import com.javaweb.system.query.SmsLogQuery;
import com.javaweb.system.service.ISmsLogService;
import com.javaweb.system.utils.AdminUtils;
import com.javaweb.system.vo.SmsLogListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 短信日志 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-04
 */
@Service
public class SmsLogServiceImpl extends BaseServiceImpl<SmsLogMapper, SmsLog> implements ISmsLogService {

    @Autowired
    private SmsLogMapper smsLogMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        SmsLogQuery smsLogQuery = (SmsLogQuery) query;
        // 查询条件
        QueryWrapper<SmsLog> queryWrapper = new QueryWrapper<>();
        // 手机号码
        if (!StringUtils.isEmpty(smsLogQuery.getMobile())) {
            queryWrapper.like("mobile", smsLogQuery.getMobile());
        }
        // 发送类型：1用户注册 2修改密码 3找回密码 4换绑手机号验证 5换绑手机号 6钱包提现 7设置支付密码 8系统通知
        if (smsLogQuery.getType() != null && smsLogQuery.getType() > 0) {
            queryWrapper.eq("type", smsLogQuery.getType());
        }
        // 状态：1成功 2失败 3待处理
        if (smsLogQuery.getStatus() != null && smsLogQuery.getStatus() > 0) {
            queryWrapper.eq("status", smsLogQuery.getStatus());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<SmsLog> page = new Page<>(smsLogQuery.getPage(), smsLogQuery.getLimit());
        IPage<SmsLog> data = smsLogMapper.selectPage(page, queryWrapper);
        List<SmsLog> smsLogList = data.getRecords();
        List<SmsLogListVo> smsLogListVoList = new ArrayList<>();
        if (!smsLogList.isEmpty()) {
            smsLogList.forEach(item -> {
                SmsLogListVo smsLogListVo = new SmsLogListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, smsLogListVo);
                // 发送类型描述
                if (smsLogListVo.getType() != null && smsLogListVo.getType() > 0) {
                    smsLogListVo.setTypeName(SmsLogConstant.SMSLOG_TYPE_LIST.get(smsLogListVo.getType()));
                }
                // 状态描述
                if (smsLogListVo.getStatus() != null && smsLogListVo.getStatus() > 0) {
                    smsLogListVo.setStatusName(SmsLogConstant.SMSLOG_STATUS_LIST.get(smsLogListVo.getStatus()));
                }
                // 添加人名称
                if (smsLogListVo.getCreateUser() > 0) {
                    smsLogListVo.setCreateUserName(AdminUtils.getName((smsLogListVo.getCreateUser())));
                }
                // 更新人名称
                if (smsLogListVo.getUpdateUser() > 0) {
                    smsLogListVo.setUpdateUserName(AdminUtils.getName((smsLogListVo.getUpdateUser())));
                }
                smsLogListVoList.add(smsLogListVo);
            });
        }
        return JsonResult.success("操作成功", smsLogListVoList, data.getTotal());
    }

    /**
     * 删除记录
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public JsonResult deleteById(Integer id) {
        if (id == null || id == 0) {
            return JsonResult.error("记录ID不能为空");
        }
        SmsLog entity = this.getById(id);
        if (entity == null) {
            return JsonResult.error("记录不存在");
        }
        return super.delete(entity);
    }

}