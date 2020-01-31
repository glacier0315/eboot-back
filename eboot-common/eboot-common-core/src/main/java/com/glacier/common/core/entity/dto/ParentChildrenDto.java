package com.glacier.common.core.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-11-09 22:05
 */
@Data
public class ParentChildrenDto implements Serializable {

    private static final long serialVersionUID = -3487750758987093736L;
    /**
     * 父级id
     */
    private String parentId;

    /**
     * 子类id
     */
    private List<String> childrenIds;
}
