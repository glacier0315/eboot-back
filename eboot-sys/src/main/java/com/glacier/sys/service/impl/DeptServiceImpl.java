package com.glacier.sys.service.impl;

import com.glacier.common.constant.Constant;
import com.glacier.common.utils.IdGen;
import com.glacier.sys.dao.DeptDao;
import com.glacier.sys.entity.Dept;
import com.glacier.sys.service.DeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author hebin
 * @version 1.0
 * @description
 * @date 2019-10-24 17:12
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptDao deptDao;

    /**
     * 保存
     *
     * @param dept
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(Dept dept) {
        if (dept.newRecord()) {
            if (!dept.isNewRecord()) {
                dept.setId(IdGen.uuid());
            }
            return deptDao.insert(dept);
        } else {
            return deptDao.update(dept);
        }
    }

    /**
     * 删除
     *
     * @param dept
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int delete(Dept dept) {
        return deptDao.delete(dept);
    }

    /**
     * 批量删除
     *
     * @param depts
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int batchDelete(List<Dept> depts) {
        int delCount = 0;
        if (depts != null && !depts.isEmpty()) {
            for (Dept dept : depts) {
                delCount += deptDao.delete(dept);
            }
        }
        return delCount;
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @Override
    public Dept findById(String id) {
        return deptDao.findById(id);
    }

    /**
     * 查找
     * @param dept
     * @return
     */
    @Override
    public List<Dept> findList(Dept dept) {
        return deptDao.findList(dept);
    }

    /**
     * 根据用户ID 查找组织机构树
     *
     * @param userId
     * @return
     */
    @Override
    public List<Dept> findTree(String userId) {
        List<Dept> depts = this.findDeptsByUsername(userId);
        List<Dept> deptList = new ArrayList<>(10);
        //
        if (depts != null && !depts.isEmpty()) {
            Iterator<Dept> iterator = depts.iterator();
            while (iterator.hasNext()) {
                Dept dept = iterator.next();
                if (dept.getParentId() == null || "0".equals(dept.getParentId())) {
                    deptList.add(dept);
                    // 删除
                    iterator.remove();
                }
            }
        }
        // 排序
        deptList.sort((o1, o2) -> o1.getOrderNum() - o2.getOrderNum());
        // 组装子类菜单
        findChildren(deptList, depts);
        return deptList;
    }

    /**
     * 递归组装菜单
     *
     * @param deptList 当前父级菜单
     * @param depts    待查询菜单
     */
    private void findChildren(List<Dept> deptList, List<Dept> depts) {
        // 为空则返回
        if (deptList == null || deptList.isEmpty() || depts == null || depts.isEmpty()) {
            return;
        }
        for (Dept parent : deptList) {
            List<Dept> children = new ArrayList<>(10);
            Iterator<Dept> iterator = depts.iterator();
            while (iterator.hasNext()) {
                Dept dept = iterator.next();
                if (parent.getId() != null && parent.getId().equals(dept.getParentId())) {
                    dept.setParentName(parent.getName());
                    dept.setLevel(parent.getLevel() + 1);
                    children.add(dept);
                    iterator.remove();
                }
            }
            parent.setChildren(children);
            children.sort((o1, o2) -> o1.getOrderNum() - o2.getOrderNum());
            findChildren(children, depts);
        }
    }

    /**
     * 根据用户ID查找所有 组织机构
     *
     * @param userId
     * @return
     */
    private List<Dept> findDeptsByUsername(String userId) {
        List<Dept> deptList = new ArrayList<>(10);
        if (userId == null) {
            return deptList;
        }
        if (Constant.ADMIN_ID.equals(userId)) {
            Dept condition = new Dept();
            deptList = deptDao.findList(condition);
        } else {
            deptList = deptDao.findDeptsByUserId(userId);
        }
        return deptList;
    }
}
