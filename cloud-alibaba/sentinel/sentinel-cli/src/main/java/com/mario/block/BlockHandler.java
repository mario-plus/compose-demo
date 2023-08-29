package com.mario.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author zxz
 * @date 2023年08月29日 10:29
 * 规则异常，比如QPS超过阈值，熔断回调
 */

public class BlockHandler {

    /**
     * 方法必须是static
     * 两者配合使用： blockHandler = "blockHandlerMethod", blockHandlerClass = BlockHandler.class
     * 方法签名参数与原方法一致，且必须要在参数的最后位置补充BlockException参数
     */
    public static String blockHandlerMethod(String name, BlockException e) {
        e.printStackTrace();
        return "sorry sentinel: limit curr-" + name;
    }
}
