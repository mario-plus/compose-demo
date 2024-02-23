package com.mario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zxz
 * @date 2024年02月23日 11:13
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SeataApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeataApplication.class, args);
    }
}
