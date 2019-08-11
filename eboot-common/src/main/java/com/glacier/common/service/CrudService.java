package com.glacier.common.service;

import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-08-11 19:34
 */
public interface CrudService<T, ID extends Serializable> {

    T get(ID id);

    int save(T entity);

    List<T> findList(T entity);

    int delete(T entity);
}
