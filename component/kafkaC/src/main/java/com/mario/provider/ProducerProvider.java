package com.mario.provider;

import com.mario.config.KafkaConfig;
import com.mario.config.ProConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * @author zxz
 * @date 2024年04月08日 20:24
 */
@Component
public class ProducerProvider {

    @Autowired
    KafkaConfig kafkaConfig;

    public KafkaProducer<String, byte[]> getProducer() {
        return producer;
    }

    private KafkaProducer<String, byte[]> producer;

    @PostConstruct
    public void initProducer() {
        ProConfig producer = kafkaConfig.getProducer();
        Properties props = new Properties();
        props.put(ProducerConfig.ACKS_CONFIG, producer.getAcks());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, producer.getKeySerializer());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, producer.getValueSerializer());
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, producer.getBatchSize());
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, producer.getBufferMemory());
        props.put(ProducerConfig.RETRIES_CONFIG, producer.getRetries());
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaConfig.getBootstrapServers());
        this.producer = new KafkaProducer<>(props);
    }
}
