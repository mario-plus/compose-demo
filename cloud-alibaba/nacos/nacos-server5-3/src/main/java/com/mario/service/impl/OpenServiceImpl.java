package com.mario.service.impl;

import com.mario.service.OpenApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author zxz
 * @date 2023年08月23日 15:50
 */
@Service
public class OpenServiceImpl implements OpenApiService {

    @Value("${spring.application.name}")
    private String serviceName;


    @Value("${server.port}")
    private Integer serverPort;

    @Override
    public String getServiceName() {
        return serviceName + ":" + serverPort;
    }

    @Override
    public String getCity() {
        return "深圳市2";
    }

}
