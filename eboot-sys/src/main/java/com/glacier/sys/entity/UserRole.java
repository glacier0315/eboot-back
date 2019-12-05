package com.glacier.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-08-11 21:18
 */
@Getter
@Setter
@ToString
public class UserRole implements Serializable {

    /**
     * id
     */
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 创建人
     */
    @JsonIgnore
    private String createBy;
    /**
     * 创建时间
     */
    private Date createDate;
}
