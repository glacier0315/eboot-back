package com.glacier.sys.service;

import com.github.pagehelper.PageInfo;
import com.glacier.core.page.PageRequest;
import com.glacier.core.service.CurdService;
import com.glacier.sys.entity.Dict;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-01 21:36
 */
public interface DictService extends CurdService<Dict> {

    /**
     * 分页查找配置
     * @param pageRequest
     * @return
     */
    PageInfo<Dict> findPage(PageRequest<Dict> pageRequest);
}
