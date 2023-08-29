package com.mario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zxz
 * @date 2023年08月29日 11:25
 * 采用sentinel限流及服务降级（openFeign远程调用，nacos服务注册中心，loadbalance做负载均衡）
 */
@SpringBootApplication
public class SonLApplication {
    public static void main(String[] args) {
        SpringApplication.run(SonLApplication.class, args);
    }
}
