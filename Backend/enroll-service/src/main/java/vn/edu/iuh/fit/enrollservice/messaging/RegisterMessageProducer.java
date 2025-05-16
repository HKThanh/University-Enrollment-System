package vn.edu.iuh.fit.enrollservice.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.enrollservice.dtos.MessageRequest;

@Service
public class RegisterMessageProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public RegisterMessageProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEnrollMessage(MessageRequest request) {
        // Send to all relevant topics for the fanout behavior
        kafkaTemplate.send("schedule-topic", request);
        kafkaTemplate.send("payment-topic", request);
        kafkaTemplate.send("notification-topic", request);
    }
}