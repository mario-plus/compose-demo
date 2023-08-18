package com.mario.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.integration.zookeeper.lock.ZookeeperLockRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author zxz
 * @date 2023年08月18日 11:03
 */
@Slf4j
@RestController
public class LockApiController {

    final CuratorFramework curatorFramework;
    final ZookeeperLockRegistry zookeeperLockRegistry;

    public LockApiController(CuratorFramework curatorFramework,
                             ZookeeperLockRegistry zookeeperLockRegistry) {
        this.curatorFramework = curatorFramework;
        this.zookeeperLockRegistry = zookeeperLockRegistry;
    }

    /**
     * 使用ApiFox进行压测
     * */
    @GetMapping(value = "/zkLockTest")
    public String zkLockTest() throws InterruptedException {
        Lock lock1 = zookeeperLockRegistry.obtain("lock1");
        if (lock1.tryLock(10, TimeUnit.MINUTES)) {
            try {
                String name = Thread.currentThread().getName();
                log.info("线程" + name + "获取到锁,开始执行事务");
                Thread.sleep(3000);//模拟业务处理耗时
                log.info("线程" + name + "事务执行完成");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
                log.info("释放锁...");
            }
        } else {
            log.info("获取锁失败");
        }
        return "end";
    }
}
