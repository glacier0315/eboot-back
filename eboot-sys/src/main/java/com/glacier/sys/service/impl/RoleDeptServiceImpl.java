package com.glacier.sys.service.impl;

import com.glacier.common.core.utils.IdGen;
import com.glacier.sys.entity.RoleDept;
import com.glacier.sys.mapper.RoleDeptMapper;
import com.glacier.sys.service.RoleDeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-05 21:02
 */
@Slf4j
@Transactional(readOnly = true)
@Service("RoleDeptService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleDeptServiceImpl implements RoleDeptService {

    private final RoleDeptMapper roleDeptMapper;

    @Transactional(rollbackFor = {})
    @Override
    public int deleteByDeptId(String deptId) {
        return roleDeptMapper.deleteByDeptId(deptId);
    }

    @Transactional(rollbackFor = {})
    @Override
    public int deleteByRoleId(String roleId) {
        return roleDeptMapper.deleteByRoleId(roleId);
    }

    @Transactional(rollbackFor = {})
    @Override
    public int insert(String roleId, List<String> deptList) {
        // 删除原数据
        int success = this.deleteByRoleId(roleId);
        // 重新保存
        if (deptList != null && !deptList.isEmpty()) {
            List<RoleDept> roleMenus = new ArrayList<>(10);
            RoleDept roleDept = null;
            for (String deptId : deptList) {
                if (deptId == null || deptId.trim().length() == 0) {
                    continue;
                }
                roleDept = new RoleDept();
                // 生成id
                roleDept.setId(IdGen.uuid());
                roleDept.setRoleId(roleId);
                roleDept.setRoleId(deptId);

                roleMenus.add(roleDept);
            }
            //
            success = roleDeptMapper.insertBatch(roleMenus);
        }
        return success;
    }
}
