package com.mario;

import com.mario.callback.KafkaMessageCallback;
import com.mario.provider.ProducerProvider;
import com.mario.service.KafkaProducerService;
import com.mario.utils.TopicAdmin;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.*;


/**
 * @author zxz
 * @date 2024年04月08日 20:20
 */
@Slf4j
@SpringBootApplication
public class KafkaClusterApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaClusterApplication.class, args);

    }

    @Autowired
    KafkaProducerService producerService;


    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 5000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    @PostConstruct
    public void sendMsg() {
        TopicAdmin.createTopics(Collections.singletonList("192.168.20.128:9092"), Collections.singletonList(new NewTopic("test4", 3, (short) 3)));
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> {
                producerService.send("test2", finalI % 2, "key" + finalI, "value" + finalI, new KafkaMessageCallback() {
                    @Override
                    public void onSuccess(RecordMetadata recordMetadata) {
                        log.info("发送消息成功，topic：{}，partition：{},offset:{}", recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
                    }

                    @Override
                    public void onError(Exception e) {
                        log.info("发送消息失败，reason：{}", e.getMessage());
                    }
                });
            });
        }


    }
}
