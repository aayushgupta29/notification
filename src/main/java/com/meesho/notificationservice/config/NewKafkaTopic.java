package com.meesho.notificationservice.config;

import org.apache.kafka.clients.admin.NewTopic;

public class NewKafkaTopic extends NewTopic {
    public NewKafkaTopic(String name, int numPartitions, short replicationFactor) {
        super(name, numPartitions, replicationFactor);
    }
}
