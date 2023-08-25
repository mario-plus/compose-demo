package com.mario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zxz
 * @date 2023年08月23日 16:18
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class OpenFeignClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenFeignClientApplication.class, args);
    }
}
