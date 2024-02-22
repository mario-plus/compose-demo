package com.mario.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxz
 * @date 2023年09月21日 20:16
 */
@RestController
public class NaCosConfigController {

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private String port;


    @GetMapping("getServerInfo")
    public String getServerInfo() {
        return applicationName + ":" + port;
    }

}
