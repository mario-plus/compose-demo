package com.mario.service;

import com.mario.balancer.CustomLoadBalancerConfig;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "nacos-server5")//指定服务注册中心的
@Component
@LoadBalancerClient(name = "nacos-server5", configuration = CustomLoadBalancerConfig.class)//负载均衡策略，name是服务名称
public interface NaCosOpenApiService2 {


    @GetMapping("/getServiceName")
    String getServiceName();

}
