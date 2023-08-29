package com.mario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zxz
 * @date 2023年08月29日 17:53
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayCliApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayCliApplication.class, args);
    }
}
