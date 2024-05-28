package com.mario.config.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @author zxz
 * @date 2024年04月08日 20:29
 */
@Configuration
@ConfigurationProperties(prefix = "spring.kafka.producer")
@Data
public class ProConfig {


    private Integer retries;


    private Integer batchSize;

    private Integer bufferMemory;

    private String keySerializer;

    private String valueSerializer;

    private String acks;
}
