package com.glacier.sys.controller;

import com.glacier.core.http.HttpResult;
import com.glacier.security.util.SecurityUtils;
import com.glacier.sys.entity.Dept;
import com.glacier.sys.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hebin
 * @version 1.0
 * @description 组织机构控制层
 * @date 2019-10-24 17:15
 */
@Slf4j
@RestController
@RequestMapping(value = "dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     * 根据用户id 查询组织机构
     *
     * @param id
     * @return
     */
    @GetMapping("get/{id}")
    public Dept get(@PathVariable("id") String id) {
        return deptService.findById(id);
    }

    /**
     * 查询所有组织机构
     *
     * @param dept
     * @return
     */
    @GetMapping("list")
    public List<Dept> list(Dept dept) {
        return deptService.findList(dept);
    }


    /**
     * 保存组织机构
     *
     * @param dept
     * @return
     */
    @PutMapping("save")
    public int save(Dept dept) {
        return deptService.save(dept);
    }

    /**
     * 删除指定组织机构
     *
     * @param id
     * @return
     */
    @DeleteMapping("del/{id}")
    public int delete(@PathVariable("id") String id) {
        Dept dept = new Dept();
        dept.setId(id);
        return deptService.delete(dept);
    }

    /**
     * 查询所有菜单 树
     *
     * @return
     */
    @GetMapping("findNavTree")
    public HttpResult findNavTree() {
        String username = SecurityUtils.getUsername();
        log.debug("username: {}", username);
        List<Dept> tree = deptService.findTree(username);
        return HttpResult.ok(tree);
    }
}
