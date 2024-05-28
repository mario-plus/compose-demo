package com.mario.producer.kafka;

import com.mario.common.QueueMsg;
import com.mario.common.SimpleCallback;
import com.mario.producer.IProducer;
import com.mario.provider.KafkaProducerProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zxz
 * @date 2024年04月09日 18:38
 */

@Component
@Slf4j
public class KafkaMqProducer<M extends QueueMsg> implements IProducer<M> {

    @Autowired
    KafkaProducerProvider producerProvider;

    @Override
    public boolean send(String topic, String partitionKey, M message) {

        KafkaProducer producer = producerProvider.getProducer();


        return false;
    }

    @Override
    public void asyncSend(String topic, String partitionKey, M message, SimpleCallback callback) {

    }
}
