package com.mario.feign;

import com.mario.balancer.CustomLoadBalancerConfig;
import com.mario.common.SeataResource;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zxz
 * @date 2024年02月23日 11:20
 */
@FeignClient(value = "nacos-seata-resource", path = "/resource")
@Component
@LoadBalancerClient(name = "nacos-seata-resource", configuration = CustomLoadBalancerConfig.class)//负载均衡策略，name是服务名称=
public interface ResourceServiceFeign {

    @GetMapping("/getResource")
    SeataResource selectById(@RequestParam("id") int id);

    @GetMapping("/disCount")
    void disCountById(@RequestParam("id") int id, @RequestParam("count") int count) throws Exception;

}
