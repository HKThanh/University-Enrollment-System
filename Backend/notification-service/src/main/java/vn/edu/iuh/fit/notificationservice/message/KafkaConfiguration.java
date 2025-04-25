package vn.edu.iuh.fit.notificationservice.message;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic notificationTopic() {
        return TopicBuilder.name("notification-topic")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic checkoutNotificationTopic() {
        return TopicBuilder.name("checkout-notification-topic")
                .partitions(1)
                .replicas(1)
                .build();
    }
}