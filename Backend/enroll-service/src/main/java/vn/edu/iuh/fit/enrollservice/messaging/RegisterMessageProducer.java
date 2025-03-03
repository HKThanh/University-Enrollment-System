package vn.edu.iuh.fit.enrollservice.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.enrollservice.dtos.MessageRequest;

@Service
public class RegisterMessageProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;


    public static final String ENROLL_EVENTS_TOPIC = "enroll-events";
    public static final String SCHEDULE_EVENTS_TOPIC = "schedule-events";
    public static final String PAYMENT_EVENTS_TOPIC = "payment-events";
    public static final String CHECKOUT_EVENTS_TOPIC = "checkout-events";
    public static final String NOTIFICATION_EVENTS_TOPIC = "notification-events";

    @Autowired
    public RegisterMessageProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEnrollMessage(MessageRequest request) {

        kafkaTemplate.send(SCHEDULE_EVENTS_TOPIC, request);
        kafkaTemplate.send(PAYMENT_EVENTS_TOPIC, request);
        kafkaTemplate.send(NOTIFICATION_EVENTS_TOPIC, request);
    }
}
