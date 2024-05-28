package com.mario.provider;

import com.mario.config.kafka.ConsConfig;
import com.mario.config.kafka.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

/**
 * @author zxz
 * @date 2024年04月09日 18:12
 */
public class KafkaConsumerProvider implements IConsumerProvider<KafkaConsumer, KafkaConfig> {

    private KafkaConsumer kafkaConsumer;

    @Override
    public KafkaConsumer getConsumer() {
        return kafkaConsumer;
    }

    @Override
    public void initClient(KafkaConfig config) {
        ConsConfig consumer = config.getConsumer();
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getBootstrapServers());
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, consumer.getAutoCommitInterval());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumer.getAutoOffsetReset());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, consumer.getMaxPollRecords());
        props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, consumer.getFetchMaxWait());
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, consumer.getHeartbeatInterval());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, consumer.getKeyDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, consumer.getValueDeserializer());
        this.kafkaConsumer = new KafkaConsumer<>(props);
    }
}
