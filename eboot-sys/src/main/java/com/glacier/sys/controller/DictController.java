package com.glacier.sys.controller;

import com.glacier.core.http.HttpResult;
import com.glacier.sys.entity.Dict;
import com.glacier.sys.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 字典控制层
 * @date 2019-12-01 21:13
 */
@Slf4j
@RestController
@RequestMapping(value = "dict")
public class DictController {
    @Resource
    private DictService dictService;

    /**
     * 查询字典
     *
     * @return
     */
    @GetMapping("findDictTree")
    public HttpResult findDictTree() {
        return HttpResult.ok(dictService.findDictTree());
    }

    /**
     * 保存字典
     *
     * @param dict
     * @return
     */
    @PostMapping("save")
    public HttpResult save(@RequestBody Dict dict) {
        return HttpResult.ok(dictService.save(dict));
    }

    /**
     * 删除指定字典
     *
     * @param dicts
     * @return
     */
    @PostMapping("delete")
    public HttpResult delete(@RequestBody List<Dict> dicts) {
        return HttpResult.ok(dictService.batchDelete(dicts));
    }
}