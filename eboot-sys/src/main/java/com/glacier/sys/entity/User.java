package com.glacier.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.glacier.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description  用户
 * @date 2019-08-04 13:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BaseEntity {
    private static final long serialVersionUID = -3083387263445135811L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 性别 1=男 2=女 其他=保密
     */
    private Integer sex;
    /**
     * 状态  1 正常  0 锁定
     */
    private String status;
    /**
     * 角色
     */
    private List<Role> roles;
}
