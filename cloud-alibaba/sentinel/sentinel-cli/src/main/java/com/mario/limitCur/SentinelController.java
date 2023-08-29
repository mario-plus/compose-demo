package com.mario.limitCur;

import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.mario.block.BlockHandler;
import com.mario.block.FallbackHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zxz
 * @date 2023年08月29日 9:40
 */
@RestController
public class SentinelController {

    @GetMapping("hello")
    public String hello() {
        boolean testSphO = SphO.entry("hello");
        //使用限流规则
        if (testSphO) {
            try {
                System.out.println("这里执行具体业务逻辑");
                return "hello sentinel";
            } finally {
                SphO.exit(); // 限流的出口
            }
        } else {
            //限流后的操作
            return "sorry sentinel: limit curr";
        }
    }

    @SentinelResource(value = "sayHello", blockHandler = "blockHandlerMethod", blockHandlerClass = BlockHandler.class)
    @GetMapping("sayHello")
    public String sayHello(String name) {
        System.out.println("这里执行具体业务逻辑");
        return "hello sentinel:" + name;
    }

    @SentinelResource(value = "sayHelloException", fallback = "businessExceptionHandler", fallbackClass = FallbackHandler.class)
    @GetMapping("sayHelloException")
    public String sayHelloException(String name) throws Exception {
        throw new Exception("business exception:" + name);

    }


}
