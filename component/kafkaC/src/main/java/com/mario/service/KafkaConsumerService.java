package com.mario.service;

import com.mario.provider.ConsumerProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @author zxz
 * @date 2024年04月09日 13:34
 */
@Service
@Slf4j
public class KafkaConsumerService {

    @Autowired
    ConsumerProvider consumerProvider;

    @PostConstruct
    public void consumerMsg() {
        KafkaConsumer<String, byte[]> consumer = consumerProvider.getConsumer();
        //订阅主题和分区
        List<TopicPartition> topicPartitions = new ArrayList<>();
        topicPartitions.add(new TopicPartition("test2", 1));
        consumer.assign(topicPartitions);
        while (true) {
            ConsumerRecords<String, byte[]> poll = consumer.poll(Duration.ofSeconds(5));
            poll.forEach(e -> log.info("receiver msg：key-{},value:{},partition:{},offset:{}", e.key(), e.value(), e.partition(), e.offset()));
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
