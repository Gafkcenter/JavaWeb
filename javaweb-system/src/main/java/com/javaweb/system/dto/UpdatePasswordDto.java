package com.javaweb.system.dto;

import lombok.Data;

/**
 * 修改密码
 */
@Data
public class UpdatePasswordDto {

    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 确认密码
     */
    private String rePassword;

}
