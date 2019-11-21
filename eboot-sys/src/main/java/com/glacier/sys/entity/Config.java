package com.glacier.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.glacier.core.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-11-06 11:11
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Config extends BaseEntity {

    private String value;

    private String label;

    private String type;

    private String description;

    private int sort;

    private String remarks;
}
