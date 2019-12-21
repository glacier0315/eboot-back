package com.glacier.auth.common.utils.jwt;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-20 15:49
 */
public interface IjwtInfo {

    /**
     * 获取用户名
     *
     * @return
     */
    String getUsername();

    /**
     * 获取用户ID
     *
     * @return
     */
    String getUserId();
}
