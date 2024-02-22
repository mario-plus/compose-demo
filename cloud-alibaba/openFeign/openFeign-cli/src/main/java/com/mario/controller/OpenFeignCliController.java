package com.mario.controller;

import com.mario.service.NaCosOpenApiService2;
import com.mario.service.NacosOpenApiService;
import com.mario.service.OpenApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxz
 * @date 2023年08月23日 16:25
 */
@RestController
public class OpenFeignCliController {

    final OpenApiService openApiService;
    final NacosOpenApiService nacosOpenApiService;
    final NaCosOpenApiService2 naCosOpenApiService2;

    public OpenFeignCliController(OpenApiService openApiService,
                                  NacosOpenApiService nacosOpenApiService,
                                  NaCosOpenApiService2 naCosOpenApiService2) {
        this.openApiService = openApiService;
        this.nacosOpenApiService = nacosOpenApiService;
        this.naCosOpenApiService2 = naCosOpenApiService2;
    }

    @GetMapping("/getServiceName")
    public String getService() {
        return openApiService.getServiceName();
    }

    @GetMapping("/getNaCosService")
    public String getNaCosServiceName() {
        return nacosOpenApiService.getServiceName();
    }


    @GetMapping("/getNaCosService2")
    public String getNaCosServiceName2() {
        return naCosOpenApiService2.getServiceName();
    }


}
