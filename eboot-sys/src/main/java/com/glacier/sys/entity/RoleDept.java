package com.glacier.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.glacier.core.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author hebin
 * @version 1.0
 * @description 角色和组织机构关联类
 * @date 2019-10-24 16:52
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDept extends BaseEntity {

    /**
     * 角色id
     */
    private String roleId;
    /**
     * 组织机构id
     */
    private String deptId;
}
