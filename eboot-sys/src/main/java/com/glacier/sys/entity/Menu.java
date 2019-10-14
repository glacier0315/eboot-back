package com.glacier.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.glacier.core.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author hebin
 * @version 1.0
 * @description 菜单
 * @date 2019-10-09 11:03
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Menu extends BaseEntity {

    private String name;

    private String url;

    private String perms;

    private Integer type;
    /**
     * 1 目录  2 菜单  3 权限标识
     */
    private Integer status;

    private String icon;

    private Integer orderNum;

    private String parentId;

    /**
     * 扩展字段
     */
    private String parentName;
    private Integer level;
    private List<Menu> children;
}
