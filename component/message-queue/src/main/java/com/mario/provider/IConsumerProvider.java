package com.mario.provider;

import com.mario.common.IMQProvider;


public interface IConsumerProvider<Consumer, MqConfig> extends IMQProvider<MqConfig> {

    Consumer getConsumer();


}
