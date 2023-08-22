package com.mario.config;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zxz
 * @date 2023年08月22日 10:58
 */
@Configuration
public class RedisClusterConfig {

    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxTotal;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Bean
    public JedisCluster getJedisCluster() {
        Set<HostAndPort> nodes = new HashSet<>();
        //分割出集群节点
        String[] cNodes = clusterNodes.split(",");
        Arrays.stream(cNodes).filter(StringUtils::isNotBlank).forEach(c -> {
            String[] hp = c.split(":");
            nodes.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
        });
        JedisCluster jedisCluster = null;
        if (nodes.size() > 0) {
            GenericObjectPoolConfig<redis.clients.jedis.Jedis> pool = new GenericObjectPoolConfig<>();
            pool.setMaxTotal(maxTotal);
            pool.setMinIdle(minIdle);
            pool.setMaxIdle(maxIdle);
            // 连接耗尽时是否阻塞, false报异常,true阻塞直到超时, 默认true
            pool.setBlockWhenExhausted(true);
            // 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
            pool.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
            // 是否启用pool的jmx管理功能, 默认true
            pool.setJmxEnabled(true);
            // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            pool.setTestOnBorrow(true);

            jedisCluster = new JedisCluster(nodes, 10 * 1000);//创建集群对象,无密码
            //jedisCluster = new JedisCluster(nodes, commandTimeout, commandTimeout, 5, password, pool);//有密码的请使用这一个
        }
        return jedisCluster;
    }
}



