package com.mario.fallbackFactory;

import com.mario.service.NacosOpenApiService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author zxz
 * @date 2023年08月25日 18:22
 */
@Component
public class NaCosOpenApiServiceCallbackFactory implements FallbackFactory<NacosOpenApiService> {
    @Override
    public NacosOpenApiService create(Throwable cause) {

        return new NacosOpenApiService() {
            @Override
            public String getServiceName() {
                return "fallbackFactory: 服务降级，熔断";
            }
        };
    }
}
