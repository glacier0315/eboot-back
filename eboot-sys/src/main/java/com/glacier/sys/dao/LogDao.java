package com.glacier.sys.dao;

import com.glacier.sys.entity.Log;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 
 * @date 2019-12-18 15:29
 */
public interface LogDao {

    /**
     * 查询
     * @param log
     * @return
     */
    List<Log> findList(Log log);

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(Log record);

    /**
     * 插入
     * @param record
     * @return
     */
    int insertSelective(Log record);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Log record);

}