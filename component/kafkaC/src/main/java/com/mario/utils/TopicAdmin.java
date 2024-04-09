package com.mario.utils;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author zxz
 * @date 2024年04月09日 15:10
 */
public class TopicAdmin {


    public static void createTopics(List<String> servers, List<NewTopic> newTopics) {
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        AdminClient admin = AdminClient.create(config);
        admin.createTopics(newTopics);

        admin.close();
    }
}
