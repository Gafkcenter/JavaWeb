package com.javaweb.system.dto;

import lombok.Data;

@Data
public class LevelDto {

    /**
     * 数据源ID(逗号分隔)
     */
    String ids;

    /**
     * 设置状态
     */
    Integer status;

}
