package com.glacier.consumer.openfeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hebin
 * @version 1.0
 * @description
 * @date 2019-10-30 12:10
 */
@FeignClient("eboot-producer")
public interface EbootProducerService {

    @RequestMapping("/hello")
    String hello();
}
