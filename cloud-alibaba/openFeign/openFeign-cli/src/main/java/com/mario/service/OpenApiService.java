package com.mario.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zxz
 * @date 2023年08月23日 15:49
 */
@FeignClient(name = "test",url = "10.3.52.147:8001")//单独的一个http接口，接口提供方没有注册到注册中心
@Component
public interface OpenApiService {

    @GetMapping("/getServiceName")
    String getServiceName();
}
