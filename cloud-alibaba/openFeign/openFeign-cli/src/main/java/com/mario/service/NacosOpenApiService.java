package com.mario.service;

import com.mario.balancer.CustomLoadBalancerConfig;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zxz
 * @date 2023年08月23日 17:06
 */
@FeignClient(value = "nacosOpenFeignServer")//指定服务注册中心的
@Component
@LoadBalancerClient(name = "nacosOpenFeignServer", configuration = CustomLoadBalancerConfig.class)//负载均衡策略，name是服务名称
public interface NacosOpenApiService {

    @GetMapping("/getServiceName")
    String getServiceName();

}
