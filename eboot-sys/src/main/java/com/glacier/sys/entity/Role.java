package com.glacier.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.glacier.core.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author glacier
 * @version 1.0
 * @description  角色
 * @date 2019-08-04 13:45
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Role extends BaseEntity {
    private static final long serialVersionUID = -3318599726827564559L;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 状态 1 正常  2 禁用
     */
    private String status;
}