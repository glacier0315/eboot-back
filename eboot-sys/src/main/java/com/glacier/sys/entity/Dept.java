package com.glacier.sys.entity;

import com.glacier.common.core.entity.BaseTreeEntity;
import lombok.*;

/**
 * @author hebin
 * @version 1.0
 * @description 组织机构
 * @date 2019-10-14 17:06
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dept extends BaseTreeEntity<Dept> {
    private static final long serialVersionUID = 7605652474322748904L;
    /**
     * 单位编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型
     */
    private int type;
    /**
     * 状态
     */
    private int status;
}
