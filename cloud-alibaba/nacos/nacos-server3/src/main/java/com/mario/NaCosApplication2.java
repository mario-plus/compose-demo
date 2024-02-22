package com.mario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author zxz
 * @date 2023年08月23日 15:16
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NaCosApplication2 {
    public static void main(String[] args) {
        SpringApplication.run(NaCosApplication2.class, args);
    }
}
