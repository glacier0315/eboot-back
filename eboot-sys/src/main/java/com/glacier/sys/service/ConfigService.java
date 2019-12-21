package com.glacier.sys.service;

import com.github.pagehelper.PageInfo;
import com.glacier.common.core.page.PageRequest;
import com.glacier.common.core.service.CurdService;
import com.glacier.sys.entity.Config;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-11-21 17:37
 */
public interface ConfigService extends CurdService<Config> {

    /**
     * 分页查找配置
     * @param pageRequest
     * @return
     */
    PageInfo<Config> findPage(PageRequest<Config> pageRequest);
}
