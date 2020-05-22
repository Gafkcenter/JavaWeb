package com.javaweb.system.query;

import com.javaweb.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 定时任务查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
@Data
public class CrontabQuery extends BaseQuery {

    /**
     * 任务标题
     */
    private String title;

    /**
     * 状态：1正常 2暂停
     */
    private Integer status;

}
