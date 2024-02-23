package com.mario.feign;

import com.mario.balancer.CustomLoadBalancerConfig;
import com.mario.common.SeataOrder;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author zxz
 * @date 2024年02月23日 11:22
 */
@FeignClient(value = "nacos-seata-order",path = "/order")
@Component
@LoadBalancerClient(name = "nacos-seata-order", configuration = CustomLoadBalancerConfig.class)//负载均衡策略，name是服务名称=
public interface OrderServiceFeign {

    @PostMapping("createOrder")
    void createOrder(@RequestBody SeataOrder seataOrder);
}
