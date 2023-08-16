package com.unilumin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxz
 * @date 2023年01月12日 15:24
 */
@RestController
public class HelloController {
    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/sayHello")
    public String sayHello(@RequestParam("content") String content) {
        return content;
    }

    @GetMapping("/fire")
    public String fire(){
        return RpcInvoker.getUserInfo();
    }
    @GetMapping("/test")
    public String sayTest(){
        return "test";
    }

}
