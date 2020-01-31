package com.glacier.sys.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-10-28 10:49
 */
@Data
public class LoginUserDto implements Serializable {
    private static final long serialVersionUID = -6313334282278917574L;
    /**
     * 类型
     */
    private String  type;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
