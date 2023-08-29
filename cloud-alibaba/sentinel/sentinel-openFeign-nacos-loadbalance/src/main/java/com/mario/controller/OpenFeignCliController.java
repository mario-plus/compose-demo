package com.mario.controller;

import com.mario.service.NacosOpenApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxz
 * @date 2023年08月23日 16:25
 */
@RestController
public class OpenFeignCliController {

    final NacosOpenApiService nacosOpenApiService;

    public OpenFeignCliController(
            NacosOpenApiService nacosOpenApiService) {
        this.nacosOpenApiService = nacosOpenApiService;
    }


    @GetMapping("/getNaCosService")
    public String getNaCosServiceName() {
        return nacosOpenApiService.getServiceName();
    }
}
