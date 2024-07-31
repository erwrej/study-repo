package ru.study.microservice1.configurations;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic taskCreateTopic() {
        return TopicBuilder
                .name("stats-create-topic")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic taskIncreaseTopic() {
        return TopicBuilder
                .name("stats-increase-topic")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
