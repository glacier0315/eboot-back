package com.glacier.sys.dao;

import com.glacier.sys.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 用户角色 关系 dao层
 * @date 2019-08-11 21:48
 */
public interface UserRoleDao {

    /**
     * 根据用户id 删除
     *
     * @param userId
     * @return
     */
    List<String> findByUserId(@Param("userId") String userId);
    /**
     * 插入操作
     *
     * @param list
     * @return
     */
    int insertBatch(List<UserRole> list);


    /**
     * 根据用户id 删除
     *
     * @param userId
     * @return
     */
    int deleteByUserId(@Param("userId") String userId);


    /**
     * 根据角色id 删除
     *
     * @param roleId
     * @return
     */
    int deleteByRoleId(@Param("roleId") String roleId);
}
