package com.glacier.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.glacier.core.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description  字典
 * @date 2019-12-01 21:16
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dict extends BaseEntity {
    /**
     * 编码名称
     */
    private String code;

    /**
     * 字典编码
     */
    private String name;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 父级id
     */
    private String parentId;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序号
     */
    private int sort;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 扩展字段
     */
    private String parentName;
    private int level;
    private List<Dict> children;
}
