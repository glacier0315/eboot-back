package com.glacier.sys.service.impl;

import com.glacier.sys.dao.DeptDao;
import com.glacier.sys.entity.Dept;
import com.glacier.sys.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Override
    public int save(Dept dept) {
        if (dept.isNewRecord()) {
            return deptDao.insert(dept);
        } else {
            return deptDao.update(dept);
        }
    }

    @Override
    public int delete(Dept dept) {
        return deptDao.delete(dept);
    }

    @Override
    public Dept findById(String id) {
        return deptDao.findById(id);
    }

    @Override
    public List<Dept> findList(Dept dept) {
        return deptDao.findList(dept);
    }
}
