package com.glacier.consumer.openfeign.service;

import com.glacier.consumer.openfeign.service.hystrix.EbootProducerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-10-30 12:10
 */
@FeignClient(name = "eboot-producer", fallback = EbootProducerHystrix.class)
public interface EbootProducerService {

    @RequestMapping("/hello")
    String hello();
}
