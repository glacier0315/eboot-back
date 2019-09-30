package com.glacier.sys.entity;

import com.glacier.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-08-11 21:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BaseEntity {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;
}
