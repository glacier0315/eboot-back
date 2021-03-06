package com.glacier.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;

/**
 * @author hebin
 * @version 1.0
 * @description 角色和组织机构关联类
 * @date 2019-10-24 16:52
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDept implements Serializable {

    private static final long serialVersionUID = 3533750178782125963L;
    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 组织机构id
     */
    private String deptId;
}
