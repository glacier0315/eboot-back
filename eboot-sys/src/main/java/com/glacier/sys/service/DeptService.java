package com.glacier.sys.service;

import com.glacier.core.service.CurdService;
import com.glacier.sys.entity.Dept;

import java.util.List;

/**
 * @author hebin
 * @version 1.0
 * @description
 * @date 2019-10-24 17:07
 */
public interface DeptService extends CurdService<Dept> {

    /**
     * 根据用户id 查找单位树
     *
     * @param userId
     * @return
     */
    List<Dept> findTree(String userId);
}
