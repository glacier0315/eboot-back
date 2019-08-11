package com.glacier.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * @author glacier
 * @version 1.0
 * @description 基类
 * @date 2019-08-04 13:48
 */
public abstract class BaseEntity extends IdEntity {
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @JsonIgnore
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

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
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

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id='" + id + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                '}';
    }
}
