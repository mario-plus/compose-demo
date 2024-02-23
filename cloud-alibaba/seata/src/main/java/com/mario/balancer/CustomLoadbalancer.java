package com.mario.balancer;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author zxz
 * @date 2023年08月25日 14:46
 * 自定义负载均衡策略
 */
public class CustomLoadbalancer implements ReactorServiceInstanceLoadBalancer {


    final String serviceId;

    ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

    public CustomLoadbalancer(String serviceId, ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider) {
        this.serviceId = serviceId;
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
    }


    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider
                .getIfAvailable(NoopServiceInstanceListSupplier::new);
        return supplier.get(request).next()
                .map(serviceInstances -> doChoseInstance(supplier, serviceInstances));
    }


    Response<ServiceInstance> doChoseInstance(ServiceInstanceListSupplier supplier,
                                              List<ServiceInstance> serviceInstances) {
        //TODO 可以做一些逻辑服务选择
        return new DefaultResponse(serviceInstances.get(0));
    }

}
