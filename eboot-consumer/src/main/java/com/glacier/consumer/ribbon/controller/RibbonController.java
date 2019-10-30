package com.glacier.consumer.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author hebin
 * @version 1.0
 * @description
 * @date 2019-10-30 11:48
 */
@RestController
@RequestMapping("ribbon")
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;

    public static final String EBOOT_PRODUCER = "eboot-producer";

    @RequestMapping("call")
    public String call() {
        // 调用服务, service-producer为注册的服务名称，LoadBalancerInterceptor会拦截调用并根据服务名找到对应的服务
        String callServiceResult = restTemplate.getForObject("http://" + EBOOT_PRODUCER + "/hello", String.class);
        return callServiceResult;
    }
}
