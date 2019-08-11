package com.glacier.sys.entity;

import com.glacier.common.entity.BaseEntity;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-08-11 21:18
 */
public class UserRole extends BaseEntity {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userId='" + userId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", id='" + id + '\'' +
                '}';
    }
}
