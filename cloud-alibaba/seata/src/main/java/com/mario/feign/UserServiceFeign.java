package com.mario.feign;

import com.mario.balancer.CustomLoadBalancerConfig;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "nacos-seata-user",path = "/user")
@Component
@LoadBalancerClient(name = "nacos-seata-user", configuration = CustomLoadBalancerConfig.class)//负载均衡策略，name是服务名称=
public interface UserServiceFeign {

    @GetMapping("/incScore")
    void incScore(@RequestParam("id") int id, @RequestParam("score") int score) throws Exception;

}
