package com.glacier.common.core.service;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 通用CURD service接口
 * @date 2019-10-14 16:02
 */
public interface CurdService<T> {
    /**
     * 保存操作
     *
     * @param record
     * @return
     */
    int save(T record);

    /**
     * 删除操作
     *
     * @param record
     * @return
     */
    int delete(T record);

    /**
     * 删除操作
     *
     * @param record
     * @return
     */
    int batchDelete(List<T> record);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    T findById(String id);

    /**
     * 查询所有
     *
     * @param record
     * @return
     */
    List<T> findList(T record);
}
