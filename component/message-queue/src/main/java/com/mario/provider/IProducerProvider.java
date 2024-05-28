package com.mario.provider;
import com.mario.common.IMQProvider;

/**
 * @author zxz
 * @date 2024年04月09日 18:01
 */
public interface IProducerProvider<Producer, MqConfig> extends IMQProvider<MqConfig> {



    Producer getProducer();

}
