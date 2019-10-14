package com.glacier.common.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author glacier
 * @version 1.0
 * @description 基类
 * @date 2019-08-04 13:48
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEntity extends IdEntity {
    private static final long serialVersionUID = 2888247672017206669L;
    /**
     * 是否新记录
     */
    private boolean isNewRecord = false;
    /**
     * 正常标记
     */
    public static final String NORMAL_FLAG = "0";
    /**
     * 删除标记
     */
    public static final String DEL_FLAG = "1";

    /**
     * 删除标记
     */
    protected String delFlag = NORMAL_FLAG;
    /**
     * 创建人
     */
    protected String createBy;
    /**
     * 创建时间
     */
    protected Date createDate;
    /**
     * 更新人
     */
    protected String updateBy;
    /**
     * 更新时间
     */
    protected Date updateDate;

    public boolean isNewRecord() {
        return isNewRecord || id == null || id.trim().length() == 0;
    }

    public void setNewRecord(boolean newRecord) {
        isNewRecord = newRecord;
    }
}
