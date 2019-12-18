package com.glacier.sys.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author glacier
 * @version 1.0
 * @description 
 * @date 2019-12-18 15:29
 */
@Getter
@Setter
@ToString
public class Log implements Serializable {
    private static final long serialVersionUID = 2402303891367907111L;
    /**
    * 主键
    */
    private String id;

    /**
    * 登录id
    */
    private String userId;

    /**
    * url
    */
    private String url;

    /**
    * 登录ip
    */
    private String ip;
    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;
    /**
    * 登录客户端
    */
    private String userAgent;

    /**
     * 耗时
     */
    long useTime;
    /**
     * 创建时间
     */
    private Date createDate;

    /* 非数据哭字段 */
    /**
     * 登录用户名
     */
    private String username;
}