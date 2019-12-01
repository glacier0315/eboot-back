package com.glacier.sys.controller;

import com.glacier.core.http.HttpResult;
import com.glacier.core.page.PageRequest;
import com.glacier.sys.entity.Config;
import com.glacier.sys.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description  配置控制层
 * @date 2019-12-01 20:43
 */
@Slf4j
@RestController
@RequestMapping(value = "config")
public class ConfigController {

    @Resource
    private ConfigService configService;

    /**
     * 分页查询用户
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("findPage")
    public HttpResult findPage(@RequestBody PageRequest<Config> pageRequest) {
        return HttpResult.ok(configService.findPage(pageRequest));
    }

    /**
     * 保存配置
     *
     * @param config
     * @return
     */
    @PostMapping("save")
    public HttpResult save(@RequestBody Config config) {
        return HttpResult.ok(configService.save(config));
    }

    /**
     * 删除指定用户
     *
     * @param configs
     * @return
     */
    @PostMapping("delete")
    public HttpResult delete(@RequestBody List<Config> configs) {
        return HttpResult.ok(configService.batchDelete(configs));
    }
}
