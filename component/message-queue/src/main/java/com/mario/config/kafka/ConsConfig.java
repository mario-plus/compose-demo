package com.mario.config.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zxz
 * @date 2024年04月08日 20:33
 */

@Configuration
@ConfigurationProperties(prefix = "spring.kafka.consumer")
@Data
public class ConsConfig {
    private boolean enableAutoCommit;

    private Integer autoCommitInterval;

    private String autoOffsetReset;

    private Integer maxPollRecords;

    private Integer fetchMaxWait;

    private Integer fetchMinSize;

    private Integer heartbeatInterval;

    private String keyDeserializer;

    private String valueDeserializer;

}
