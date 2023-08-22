package com.mario.test;

import com.mario.lock.RedisLockUtil;
import com.mario.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    RedisLockUtil redisLockUtil;

    @PostConstruct
    public void test() {

        redisService.set("m", String.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10)));
        System.out.println(redisService.get("m"));


        jedisCluster.set("mario", "value");
        System.out.println(jedisCluster.get("mario"));


        //模拟多线程抢占锁
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                while (true) {
                    boolean m = redisLockUtil.getLock("m");
                    if (!m) {
                        System.out.print(".");
                    } else {
                        System.out.println(LocalDateTime.now() + "抢锁成功：" + Thread.currentThread().getName() + "执行业务");
                        sleep(4000L);//模拟业务执行
                        redisLockUtil.unLock("m");//释放锁
                    }
                    sleep(1000L);//休眠后接着抢锁
                }

            }).start();
        }
        sleep(3000L);
        redisService.delete("m");
    }

    public void sleep(Long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
