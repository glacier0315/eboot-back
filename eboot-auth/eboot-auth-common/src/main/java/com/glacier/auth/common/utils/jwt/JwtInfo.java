package com.glacier.auth.common.utils.jwt;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-20 15:51
 */
public class JwtInfo implements IjwtInfo, Serializable {
    private static final long serialVersionUID = 6304539283456574825L;
    private String username;
    private String userId;

    public JwtInfo() {
    }

    public JwtInfo(String username, String userId) {
        this.username = username;
        this.userId = userId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "JwtInfo{" +
                "username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
