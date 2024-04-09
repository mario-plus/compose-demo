package com.mario.service;

import com.mario.callback.KafkaMessageCallback;
import com.mario.provider.ProducerProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author zxz
 * @date 2024年04月09日 11:27
 */
@Service
@Slf4j
public class KafkaProducerService {

    @Autowired
    ProducerProvider producerProvider;

    public void send(String topic, Integer partition, String key, String value, KafkaMessageCallback callback) {
        ProducerRecord producerRecord = new ProducerRecord<>(topic, partition, key, value);
        Future send = producerProvider.getProducer().send(producerRecord);
        try {
            RecordMetadata metadata = (RecordMetadata) send.get();
            log.info("send message success,topic:{},partition:{}", metadata.topic(), metadata.partition());
            callback.onSuccess(metadata);
        } catch (Exception e) {
            log.error("failed to sendMessage，reason：{}", e.getMessage());
            callback.onError(e);
        }


    }

    public void asyncSend(String topic, Integer partition, String key, String value, KafkaMessageCallback callback) {
        ProducerRecord producerRecord = new ProducerRecord<>(topic, partition, key, value);
        producerProvider.getProducer().send(producerRecord, (recordMetadata, e) -> {
            if (e != null) {
                log.error("failed to sendMessage，reason：{}", e.getMessage());
                callback.onError(e);
            } else {
                log.info("send message success,topic:{},partition:{}", recordMetadata.topic(), recordMetadata.partition());
                callback.onSuccess(recordMetadata);
            }
        });
    }
}
