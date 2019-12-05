package com.glacier.sys.service;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-05 20:53
 */
public interface RoleDeptService {

    /**
     * 根据单位id 删除
     *
     * @param deptId
     * @return
     */
    int deleteByDeptId(String deptId);


    /**
     * 根据角色id 删除
     *
     * @param roleId
     * @return
     */
    int deleteByRoleId(String roleId);

    /**
     * 保存
     * @param roleId
     * @param deptList
     * @return
     */
    int insert(String roleId, List<String> deptList);
}
