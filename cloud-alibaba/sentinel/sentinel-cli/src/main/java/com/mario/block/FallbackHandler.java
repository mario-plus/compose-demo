package com.mario.block;

import org.springframework.stereotype.Component;

/**
 * @author zxz
 * @date 2023年08月29日 10:43
 * 远程调用异常降级处理
 */
@Component
public class FallbackHandler {


    /**
     * 方法必须是static
     * 两者配合使用： fallback = "businessExceptionHandler", fallbackClass = FallbackHandler.class
     * */
    public static String businessExceptionHandler(String name) {
        return "业务执行异常" + name;
    }

}
