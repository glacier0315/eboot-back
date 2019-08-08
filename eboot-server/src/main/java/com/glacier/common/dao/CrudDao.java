package com.glacier.common.dao;


import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-08-04 21:54
 */
public interface CrudDao<T,ID extends Serializable> {

    T get(ID id);

    int insert(T entity);

    List<T> findList(T entity);

    int delete(T entity);
}
