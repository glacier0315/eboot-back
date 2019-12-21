package com.glacier.common.core.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-11-09 22:05
 */
@Getter
@Setter
@ToString
public class OneToManyVo implements Serializable {

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
