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
 * @description 组织机构
 * @date 2019-10-14 17:06
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dept extends BaseEntity {
    /**
     * 单位编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 父级id
     */
    private String parentId;
    /**
     * 类型
     */
    private int type;
    /**
     * 状态
     */
    private int status;
    /**
     * 排序号
     */
    private int orderNum;
    /**
     * 层级
     */
    private int level;

    // 非数据库字段
    /**
     * 下级单位
     */
    private List<Dept> children;
    /**
     * 父级单位名称
     */
    private String parentName;
}
