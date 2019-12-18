package com.glacier.sys.service;

import com.github.pagehelper.PageInfo;
import com.glacier.core.page.PageRequest;
import com.glacier.sys.entity.Log;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-18 16:08
 */
public interface LogService {
    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    PageInfo<Log> findPage(PageRequest<Log> pageRequest);

    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insert(Log record);

    /**
     * 异步插入
     * @param userId
     * @param url
     * @param ip
     * @param userAgent
     * @param useTime
     * @return
     */
    void insert(String userId, String url, String ip, String userAgent, long useTime);
}
