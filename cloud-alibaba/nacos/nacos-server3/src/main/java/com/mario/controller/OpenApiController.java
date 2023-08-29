package com.mario.controller;

import com.mario.service.OpenApiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxz
 * @date 2023年08月23日 15:52
 */
@RestController
public class OpenApiController {

    final OpenApiService openApiService;

    public OpenApiController(OpenApiService openApiService) {
        this.openApiService = openApiService;
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/getServiceName")
    public String getServiceName() throws Exception {
        return openApiService.getServiceName();
    }
}
