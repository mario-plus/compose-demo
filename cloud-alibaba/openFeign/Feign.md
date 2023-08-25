OpenFeign 提供了一种声明式的远程调用接口，它可以大幅简化远程调用
JDK动态代理
    扫描@FeignClient修饰的接口，为每一个FeignClient接口生成动态代理对象，然后注入到spring容器
    同时将代理对象添加到LocalService（负责调度）
Loadbalancer 负载均衡
    ReflectiveFeign.invoke
    LoadBalancerUtils.executeWithLoadBalancerLifecycleProcessing===>feignClient.execute
    loadBalancerClient.choose(serviceId, lbRequest)
    
     
    