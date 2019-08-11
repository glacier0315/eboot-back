package com.glacier.common.dao;


import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description dao层基类
 * @date 2019-08-04 21:54
 */
public interface CrudDao<T,ID extends Serializable> {
    /**
     * 根据主键查询
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
     * 插入
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 更新
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * 删除实体
     * @param entity
     * @return
     */
    int delete(T entity);
}
