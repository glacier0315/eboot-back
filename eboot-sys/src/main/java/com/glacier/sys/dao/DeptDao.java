package com.glacier.sys.dao;

import com.glacier.core.dao.CurdDao;
import com.glacier.sys.entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hebin
 * @version 1.0
 * @description 组织机构dao层
 * @date 2019-10-24 16:55
 */
public interface DeptDao extends CurdDao<Dept> {

    /**
     * 查询所有
     *
     * @return
     */
    List<Dept> findAllList();

    /**
     * 根据角色id 查询所有拥有权限的额组织机构id
     *
     * @param roleId
     * @return
     */
    List<Dept> findDeptsByRoleId(@Param("roleId") String roleId);

    /**
     * 根据用户名查找
     *
     * @param userId
     * @return
     */
    List<Dept> findDeptsByUserId(@Param("userId") String userId);


}
