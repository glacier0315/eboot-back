package com.glacier.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author glacier
 * @version 1.0
 * @description 基类
 * @date 2019-08-04 13:48
 */
@Getter
@Setter
@ToString(exclude = {"newRecord"})
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
    @JsonIgnore
    private String createBy;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新人
     */
    @JsonIgnore
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 是否新记录
     */
    @JsonIgnore
    private boolean newRecord = false;

    @JsonIgnore
    public boolean isNewRecord() {
        return newRecord || id == null || id.trim().length() == 0;
    }

}
