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
    public String getServiceName() throws Exception {
        Thread.sleep(10 * 1000);
        throw new Exception("模拟服务异常，测试远程调度");
        // return serviceName + ":" + serverPort;
    }

    @Override
    public String getProvince() {
        return "广东省2";
    }
}
