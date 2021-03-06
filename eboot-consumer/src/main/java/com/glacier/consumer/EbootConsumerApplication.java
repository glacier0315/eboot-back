package com.glacier.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-10-29 17:40
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class EbootConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbootConsumerApplication.class, args);
    }

    /**
     * 负载均衡
     *
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    /**
//     * 此配置是为了服务监控而配置，与服务容错本身无关，
//     * ServletRegistrationBean因为springboot的默认路径不是"/hystrix.stream"，
//     * 只要在自己的项目里配置上下面的servlet就可以了
//     *
//     * @return
//     */
//    @Bean
//    public ServletRegistrationBean getServlet() {
//        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
//        registrationBean.setLoadOnStartup(1);
//        registrationBean.addUrlMappings("/hystrix.stream");
//        registrationBean.setName("HystrixMetricsStreamServlet");
//        return registrationBean;
//    }
}
