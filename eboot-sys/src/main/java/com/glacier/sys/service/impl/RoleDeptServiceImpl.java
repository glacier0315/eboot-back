package com.glacier.sys.service.impl;

import com.glacier.common.utils.IdGen;
import com.glacier.sys.dao.RoleDeptDao;
import com.glacier.sys.entity.RoleDept;
import com.glacier.sys.service.RoleDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
public class RoleDeptServiceImpl implements RoleDeptService {
    @Resource
    private RoleDeptDao roleDeptDao;

    @Transactional(rollbackFor = {})
    @Override
    public int deleteByDeptId(String deptId) {
        return roleDeptDao.deleteByDeptId(deptId);
    }

    @Transactional(rollbackFor = {})
    @Override
    public int deleteByRoleId(String roleId) {
        return roleDeptDao.deleteByRoleId(roleId);
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
            success = roleDeptDao.insertBatch(roleMenus);
        }
        return success;
    }
}
