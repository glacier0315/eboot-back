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

    private static final long serialVersionUID = 1207728347319595982L;
    private String name;

    private String url;

    private String perms;
    /**
     * 1 目录  2 菜单  3 权限标识
     */
    private int type;
    /**
     * 打开方式  默认 1 正常  2 iframe  3 新窗口
     */
    private int open;
    /**
     * 1 正常  2 隐藏
     */
    private int status;

    private String icon;

    private int orderNum;

    private String parentId;

    /**
     * 扩展字段
     */
    private String parentName;
    private int level;
    private List<Menu> children;
}
