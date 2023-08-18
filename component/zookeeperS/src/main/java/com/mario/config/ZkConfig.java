package com.mario.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.zookeeper.lock.ZookeeperLockRegistry;

/**
 * @author zxz
 * @date 2023年08月18日 10:07
 */
@Slf4j
@Configuration
public class ZkConfig {
    @Value("${zookeeper.host}")
    private String host;



    @Bean
    public CuratorFramework curatorFramework() {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(host)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(500, 5))
                .build();
        curatorFramework.start();
        return curatorFramework;
    }

    /**
     * 创建分布式锁
     */
    @Bean
    public ZookeeperLockRegistry zookeeperLockRegistry(CuratorFramework curatorFramework) {
        return new ZookeeperLockRegistry(curatorFramework, "/locks");
    }
}
