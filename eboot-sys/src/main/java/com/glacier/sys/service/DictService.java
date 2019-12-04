package com.glacier.sys.service;

import com.glacier.core.service.CurdService;
import com.glacier.sys.entity.Dict;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-01 21:36
 */
public interface DictService extends CurdService<Dict> {

    /**
     * 查找字典
     * @return
     */
    List<Dict> findDictTree();
}
