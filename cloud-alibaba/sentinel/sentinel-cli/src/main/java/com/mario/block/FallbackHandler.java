package com.mario.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;
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
     * fallback可以处理所有类型异常，包括限流异常和业务异常
     * 使用fallback时，方法签名参数可以与原方法完全一致，或者也接受在参数的最后位置补充Throwable参数
     */
    public static String businessExceptionHandler(String name, Throwable throwable) {
        if (throwable instanceof BlockException) {
            return "限流异常" + name;
        } else {
            System.out.println("业务异常！");
        }
        return "业务执行异常" + name;
    }

}
