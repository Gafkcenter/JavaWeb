package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.common.BaseQuery;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.constant.OperLogConstant;
import com.javaweb.system.entity.OperLog;
import com.javaweb.system.mapper.OperLogMapper;
import com.javaweb.system.query.OperLogQuery;
import com.javaweb.system.service.IOperLogService;
import com.javaweb.system.vo.OperLogListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-05-04
 */
@Service
public class OperLogServiceImpl extends BaseServiceImpl<OperLogMapper, OperLog> implements IOperLogService {

    @Autowired
    private OperLogMapper operLogMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        OperLogQuery operLogQuery = (OperLogQuery) query;
        // 查询条件
        QueryWrapper<OperLog> queryWrapper = new QueryWrapper<>();
        // 模块标题
        if (!StringUtils.isEmpty(operLogQuery.getTitle())) {
            queryWrapper.like("title", operLogQuery.getTitle());
        }
        // 业务类型：0其它 1新增 2修改 3删除
        if (operLogQuery.getBusinessType() != null && operLogQuery.getBusinessType() > 0) {
            queryWrapper.eq("business_type", operLogQuery.getBusinessType());
        }
        // 操作类别：0其它 1后台用户 2手机端用户
        if (operLogQuery.getOperatorType() != null && operLogQuery.getOperatorType() > 0) {
            queryWrapper.eq("operator_type", operLogQuery.getOperatorType());
        }
        // 操作状态：1正常 2异常
        if (operLogQuery.getStatus() != null && operLogQuery.getStatus() > 0) {
            queryWrapper.eq("status", operLogQuery.getStatus());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询数据
        IPage<OperLog> page = new Page<>(operLogQuery.getPage(), operLogQuery.getLimit());
        IPage<OperLog> data = operLogMapper.selectPage(page, queryWrapper);
        List<OperLog> operLogList = data.getRecords();
        List<OperLogListVo> operLogListVoList = new ArrayList<>();
        if (!operLogList.isEmpty()) {
            operLogList.forEach(item -> {
                OperLogListVo operLogListVo = new OperLogListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, operLogListVo);
                // 业务类型描述
                if (operLogListVo.getBusinessType() != null && operLogListVo.getBusinessType() > 0) {
                    operLogListVo.setBusinessTypeName(OperLogConstant.OPERLOG_BUSINESSTYPE_LIST.get(operLogListVo.getBusinessType()));
                }
                // 操作类别描述
                if (operLogListVo.getOperatorType() != null && operLogListVo.getOperatorType() > 0) {
                    operLogListVo.setOperatorTypeName(OperLogConstant.OPERLOG_OPERATORTYPE_LIST.get(operLogListVo.getOperatorType()));
                }
                // 操作状态描述
                if (operLogListVo.getStatus() != null && operLogListVo.getStatus() > 0) {
                    operLogListVo.setStatusName(OperLogConstant.OPERLOG_STATUS_LIST.get(operLogListVo.getStatus()));
                }
                operLogListVoList.add(operLogListVo);
            });
        }
        return JsonResult.success("操作成功", operLogListVoList, data.getTotal());
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
        OperLog entity = this.getById(id);
        if (entity == null) {
            return JsonResult.error("记录不存在");
        }
        return super.delete(entity);
    }

    /**
     * 创建系统操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(OperLog operLog) {
        operLogMapper.insertOperlog(operLog);
    }

}