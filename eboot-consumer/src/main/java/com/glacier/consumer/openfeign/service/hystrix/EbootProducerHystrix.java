package com.glacier.consumer.openfeign.service.hystrix;

import com.glacier.consumer.openfeign.service.EbootProducerService;
import org.springframework.stereotype.Component;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-10-30 12:21
 */
@Component
public class EbootProducerHystrix implements EbootProducerService {

    @Override
    public String hello() {
        return "sorry, hello service call failed.";
    }
}
