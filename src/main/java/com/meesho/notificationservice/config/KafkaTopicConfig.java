package com.meesho.notificationservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaTopicConfig {
    @Bean
    public NewKafkaTopic createNewTopic() {
        return (NewKafkaTopic) TopicBuilder.name("notification-service").build();
    }
}
