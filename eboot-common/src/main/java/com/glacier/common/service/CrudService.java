package com.glacier.common.service;

import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 业务成基类
 * @date 2019-08-11 19:34
 */
public interface CrudService<T, ID extends Serializable> {
    /**
     * 查询单个
     * @param id
     * @return
     */
    T get(ID id);

    /**
     * 根据条件查询
     * @param entity
     * @return
     */
    List<T> findList(T entity);

    /**
     * 保存实体
     * @param entity
     * @return
     */
    int save(T entity);

    /**
     * 删除实体
     * @param entity
     * @return
     */
    int delete(T entity);
}
