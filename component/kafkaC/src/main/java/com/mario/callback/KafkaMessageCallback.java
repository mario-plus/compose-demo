package com.mario.callback;

import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @author zxz
 * @date 2024年04月09日 11:38
 */
public interface KafkaMessageCallback extends Callback {

    void onSuccess(RecordMetadata recordMetadata);


    void onError(Exception e);
}
