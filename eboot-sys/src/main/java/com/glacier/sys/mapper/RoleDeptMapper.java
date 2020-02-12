package com.glacier.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glacier.sys.entity.RoleDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 角色和组织机构关联dao层
 * @date 2019-10-24 17:03
 */
public interface RoleDeptMapper extends BaseMapper<RoleDept> {
    /**
     * 插入操作
     *
     * @param list
     * @return
     */
    int insertBatch(List<RoleDept> list);


    /**
     * 根据机构id 删除
     *
     * @param deptId
     * @return
     */
    int deleteByDeptId(@Param("deptId") String deptId);


    /**
     * 根据角色id 删除
     *
     * @param roleId
     * @return
     */
    int deleteByRoleId(@Param("roleId") String roleId);
}
