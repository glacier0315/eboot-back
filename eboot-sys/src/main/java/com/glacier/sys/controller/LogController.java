package com.glacier.sys.controller;

import com.glacier.core.http.HttpResult;
import com.glacier.core.page.PageRequest;
import com.glacier.sys.entity.Log;
import com.glacier.sys.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-18 16:12
 */
@Slf4j
@RestController
@RequestMapping("log")
public class LogController {

    @Resource
    private LogService logService;

    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("findPage")
    public HttpResult findPage(@RequestBody PageRequest<Log> pageRequest) {
        return HttpResult.ok(logService.findPage(pageRequest));
    }
}
