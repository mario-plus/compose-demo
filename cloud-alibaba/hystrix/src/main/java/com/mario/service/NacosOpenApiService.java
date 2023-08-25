package com.mario.service;

import com.mario.fallback.NaCosOpenApiServiceCallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zxz
 * @date 2023年08月23日 17:06
 */
@FeignClient(value = "nacos-server", fallback = NaCosOpenApiServiceCallback.class)//指定服务注册中心的
@Component
public interface NacosOpenApiService {

    @GetMapping("/getServiceName")
    String getServiceName();

}
