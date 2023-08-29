package com.mario.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.mario.block.BlockHandler;
import com.mario.block.FallbackHandler;
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

    @GetMapping("/getNaCosServiceName")
    @SentinelResource(value = "getNaCosServiceName",
            fallbackClass = FallbackHandler.class, fallback = "businessOpenFeignHandler",
            blockHandlerClass = BlockHandler.class, blockHandler = "openFeignHandler")
    public String getNaCosServiceName() throws Exception {
        try {
            return nacosOpenApiService.getServiceName();
        } catch (Exception e) {
            throw new Exception("业务执行异常");
        }
    }


    @GetMapping("sayHello")
    @SentinelResource(value = "sayHello", blockHandlerClass = BlockHandler.class, blockHandler = "blockHandlerMethod")
    public String sayHello(String name) {
        return "业务调用" + name;
    }

    @SentinelResource(value = "sayHelloEx", fallbackClass = FallbackHandler.class, fallback = "businessExceptionHandler")
    @GetMapping("sayHelloEx")
    public String sayHelloEx(String name) throws NoSuchMethodException {
        throw new NoSuchMethodException("模拟异常");
    }
}
