package com.mario.fallback;

import com.mario.service.NacosOpenApiService;
import org.springframework.stereotype.Component;

/**
 * @author zxz
 * @date 2023年08月25日 18:09
 */
@Component
public class NaCosOpenApiServiceCallback implements NacosOpenApiService {
    @Override
    public String getServiceName() {
        return "fallback:服务异常，熔断";
    }
}
