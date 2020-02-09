package com.glacier.sys.entity;

import com.glacier.common.core.entity.BaseEntity;
import lombok.*;

/**
 * @author glacier
 * @version 1.0
 * @description  角色
 * @date 2019-08-04 13:45
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends BaseEntity {
    private static final long serialVersionUID = -3318599726827564559L;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 状态 1 正常  2 禁用
     */
    private String status;
}