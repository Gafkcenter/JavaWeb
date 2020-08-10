package com.javaweb.system.service;

import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.dto.LevelDto;
import com.javaweb.system.entity.Level;
import com.javaweb.common.common.IBaseService;

/**
 * <p>
 * 职级 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
public interface ILevelService extends IBaseService<Level> {

    /**
     * 批量设置状态
     *
     * @param levelDto 状态Dto
     * @return
     */
    JsonResult batchStatus(LevelDto levelDto);

}