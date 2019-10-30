package com.glacier.consumer.controller.loadbalancer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author hebin
 * @version 1.0
 * @description
 * @date 2019-10-30 09:41
 */
@RestController
@RequestMapping("first")
public class FirstController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private DiscoveryClient discoveryClient;

    public static final String EBOOT_PRODUCER = "eboot-producer";

    /**
     * 选择一个服务
     *
     * @return
     */
    @GetMapping("")
    public String index() {
        return loadBalancerClient.choose(EBOOT_PRODUCER).getUri().toString();
    }

    /**
     * 获取所有服务
     *
     * @return
     */
    @GetMapping("services")
    public Object services() {
        return discoveryClient.getInstances(EBOOT_PRODUCER);
    }

    @GetMapping("call")
    public String call() {
        ServiceInstance instance = loadBalancerClient.choose(EBOOT_PRODUCER);
        System.out.println("服务地址：\t" + instance.getUri());
        System.out.println("服务名称：\t" + instance.getServiceId());
        String callServiceResult = new RestTemplate().getForObject(instance.getUri().toString() + "/hello", String.class);
        System.out.println(callServiceResult);
        return callServiceResult;
    }
}
