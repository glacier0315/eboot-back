package com.glacier.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glacier.common.core.page.PageRequest;
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
    Page<Log> findPage(PageRequest<Log> pageRequest);

    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insert(Log record);

    /**
     * 异步调用保存
     *
     * @param record
     */
    void insertAsync(Log record);
}
