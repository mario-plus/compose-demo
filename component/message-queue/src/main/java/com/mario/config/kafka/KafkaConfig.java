package com.mario.config.kafka;

import com.mario.common.MqConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author zxz
 * @date 2024年04月09日 11:15
 */

@Configuration
@ConfigurationProperties(prefix = "spring.kafka")
@Data
public class KafkaConfig implements MqConfig {

    private List<String> bootstrapServers;

    private ConsConfig consumer;

    private ProConfig producer;

}
