package com.glacier.sys.entity;

import com.glacier.common.core.entity.BaseTreeEntity;
import lombok.*;

/**
 * @author glacier
 * @version 1.0
 * @description  字典
 * @date 2019-12-01 21:16
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dict extends BaseTreeEntity<Dict> {
    private static final long serialVersionUID = -8004367732541881835L;
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
     * 描述
     */
    private String description;
    /**
     * 备注
     */
    private String remarks;
}
