package com.glacier.consumer.openfeign.controller;

import com.glacier.consumer.openfeign.service.EbootProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author glacier
 * @version 1.0
 * @description feign测试
 * @date 2019-10-30 12:08
 */
@RestController
@RequestMapping("feign")
public class FeiginController {

    @Autowired
    private EbootProducerService ebootProducerService;

    @GetMapping("call")
    public String call() {
        return ebootProducerService.hello();
    }
}
