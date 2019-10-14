package com.glacier.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @description 主键基类
 * @date 2019-08-11 19:31
 */
@Getter
@Setter
@ToString
public abstract class IdEntity implements Serializable {
    private static final long serialVersionUID = -7805787197851972745L;
    /**
     * 主键
     */
    protected String id;
}
