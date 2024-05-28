package com.mario.provider;

import com.mario.config.kafka.KafkaConfig;
import com.mario.config.kafka.ProConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;


/**
 * @author zxz
 * @date 2024年04月09日 18:03
 */
public class KafkaProducerProvider implements IProducerProvider<KafkaProducer, KafkaConfig> {

    private KafkaProducer producer;

    @Override
    public KafkaProducer getProducer() {
        return producer;
    }

    @Override
    public void initClient(KafkaConfig config) {
        ProConfig producer = config.getProducer();
        Properties props = new Properties();
        props.put(ProducerConfig.ACKS_CONFIG, producer.getAcks());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, producer.getKeySerializer());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, producer.getValueSerializer());
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, producer.getBatchSize());
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, producer.getBufferMemory());
        props.put(ProducerConfig.RETRIES_CONFIG, producer.getRetries());
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getBootstrapServers());
        this.producer = new KafkaProducer<>(props);
    }
}
