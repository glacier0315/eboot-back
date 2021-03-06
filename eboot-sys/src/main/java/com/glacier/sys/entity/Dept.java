package com.glacier.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author hebin
 * @version 1.0
 * @description 组织机构
 * @date 2019-10-14 17:06
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(excludeProperty = {"level", "children", "parentName"})
public class Dept implements Serializable {
    private static final long serialVersionUID = 7605652474322748904L;
    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 单位编码
     */
    private String code;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 父级id 顶级id默认为0
     */
    private String parentId;
    /**
     * 排序号
     */
    private Integer orderNum;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 删除标记
     */
    private String delFlag;
    /**
     * 层级
     */
    private Integer level;
    /**
     * 下级单位
     */
    private List<Dept> children;
    /**
     * 父级名称
     */
    private String parentName;
}
