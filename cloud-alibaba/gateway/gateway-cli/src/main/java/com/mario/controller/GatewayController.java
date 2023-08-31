package com.mario.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxz
 * @date 2023年08月30日 10:51
 */
@RestController
public class GatewayController {

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${server.port}")
    private String port;

    @GetMapping("getServiceName2")
    public String getServiceName() {
        return serviceName + port;
    }
}
