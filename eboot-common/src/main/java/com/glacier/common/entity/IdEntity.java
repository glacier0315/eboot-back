package com.glacier.common.entity;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @description 主键基类
 * @date 2019-08-11 19:31
 */
public abstract class IdEntity implements Serializable {
    private static final long serialVersionUID = -7805787197851972745L;
    /**
     * 主键
     */
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IdEntity{" +
                "id='" + id + '\'' +
                '}';
    }
}
