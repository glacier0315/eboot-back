package com.glacier.security.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-10-28 10:49
 */
@Data
public class LoginBean implements Serializable {
    private static final long serialVersionUID = -6313334282278917574L;
    /**
     * 用户名
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String captcha;
}
