package com.mario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zxz
 * @date 2024年02月22日 21:06
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SeateUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeateUserApplication.class, args);
    }
}
