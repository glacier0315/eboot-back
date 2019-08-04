package com.glacier.common.dao;


import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-08-04 21:54
 */
public interface CrudDao<T> {
    int insert(T entity);

    List<T> findList(T entity);

    int delete(T entity);
}
