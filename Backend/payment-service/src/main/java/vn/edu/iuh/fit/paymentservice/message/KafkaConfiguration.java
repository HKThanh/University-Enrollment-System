package vn.edu.iuh.fit.paymentservice.message;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic paymentTopic() {
        return TopicBuilder.name("payment-topic")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic checkoutTopic() {
        return TopicBuilder.name("checkout-topic")
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