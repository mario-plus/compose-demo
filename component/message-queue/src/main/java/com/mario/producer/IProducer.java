package com.mario.producer;

import com.mario.common.SimpleCallback;

public interface IProducer<M> {


    boolean send(String topic, String partitionKey, M message);


    void asyncSend(String topic, String partitionKey, M message, SimpleCallback callback);

}
