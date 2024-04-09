package com.mario.provider;

import com.mario.config.ConsConfig;
import com.mario.config.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * @author zxz
 * @date 2024年04月09日 11:29
 */
@Component
public class ConsumerProvider {


    @Autowired
    KafkaConfig kafkaConfig;


    public KafkaConsumer<String, byte[]> getConsumer() {
        return consumer;
    }

    private KafkaConsumer<String, byte[]> consumer;

    @PostConstruct
    private void initConsumer() {
        ConsConfig consumer = kafkaConfig.getConsumer();
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getBootstrapServers());
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,consumer.getAutoCommitInterval());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,consumer.getAutoOffsetReset());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,consumer.getMaxPollRecords());
        props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG,consumer.getFetchMaxWait());
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG,consumer.getHeartbeatInterval());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,consumer.getKeyDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,consumer.getValueDeserializer());
        this.consumer = new KafkaConsumer<>(props);
    }

}
