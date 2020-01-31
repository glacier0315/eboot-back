package com.glacier.sys.entity;

import com.glacier.common.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-11-06 11:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Config extends BaseEntity {

    private static final long serialVersionUID = 3535850238275216235L;
    /**
     * 值
     */
    private String value;
    /**
     * 显示名称
     */
    private String label;
    /**
     * 类型
     */
    private String type;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序号
     */
    private int orderNum;
    /**
     * 备注
     */
    private String remarks;
}
