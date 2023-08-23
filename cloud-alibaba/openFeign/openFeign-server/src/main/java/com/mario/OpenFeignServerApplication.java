package com.mario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zxz
 * @date 2023年08月23日 16:09
 */
@EnableFeignClients
@SpringBootApplication
public class OpenFeignServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpenFeignServerApplication.class, args);
    }
}
