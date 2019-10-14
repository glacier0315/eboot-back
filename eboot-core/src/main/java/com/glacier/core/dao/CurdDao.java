package com.glacier.core.dao;

import java.util.List;

/**
 * @author hebin
 * @version 1.0
 * @description 通用CURD dao层接口
 * @date 2019-10-14 16:14
 */
public interface CurdDao<T> {
    /**
     * 插入操作
     *
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 更新操作
     *
     * @param record
     * @return
     */
    int update(T record);

    /**
     * 删除操作
     *
     * @param record
     * @return
     */
    int delete(T record);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    T findById(String id);

    /**
     * 根据条件查询
     *
     * @param record
     * @return
     */
    List<T> findList(T record);
}
