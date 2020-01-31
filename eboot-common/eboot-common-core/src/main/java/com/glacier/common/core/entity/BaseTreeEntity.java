package com.glacier.common.core.entity;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description  树形基类
 * @date 2020-01-31 08:48
 */
public abstract class BaseTreeEntity<T extends BaseTreeEntity> extends BaseEntity {
    private static final long serialVersionUID = -5394280029542193271L;
    /**
     * 父级id 顶级id默认为0
     */
    private String parentId;
    /**
     * 层级
     */
    private int level;
    /**
     * 排序号
     */
    private int orderNum;
    /**
     * 下级单位
     */
    private List<T> children;
    /**
     * 父级名称
     */
    private String parentName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
