package com.mario.test;

import com.mario.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author zxz
 * @date 2023年08月22日 10:45
 */
@Component
public class Test {

    @Autowired
    RedisService redisService;

    @Autowired
    JedisCluster jedisCluster;

    @PostConstruct
    public void test() {

        System.out.println("mario");
        redisService.set("n", "sss");
        System.out.println(redisService.get("m"));
        System.out.println(redisService.get("n"));


        jedisCluster.set("mario","value");
        System.out.println(jedisCluster.get("mario"));
    }
}
