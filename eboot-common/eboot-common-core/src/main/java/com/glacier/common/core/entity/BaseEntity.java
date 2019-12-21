package com.glacier.common.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author glacier
 * @version 1.0
 * @description 基类
 * @date 2019-08-04 13:48
 */
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 2888247672017206669L;

    /**
     * 正常标记
     */
    public static final String NORMAL_FLAG = "0";
    /**
     * 删除标记
     */
    public static final String DEL_FLAG = "1";


    /**
     * 主键
     */
    private String id;
    /**
     * 删除标记
     */
    private String delFlag = NORMAL_FLAG;
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
     * 是否新记录 默认为false
     */
    private boolean isNewRecord = false;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isNewRecord() {
        return isNewRecord || id == null || id.trim().isEmpty();
    }

    public void setNewRecord(boolean newRecord) {
        isNewRecord = newRecord;
    }
}
