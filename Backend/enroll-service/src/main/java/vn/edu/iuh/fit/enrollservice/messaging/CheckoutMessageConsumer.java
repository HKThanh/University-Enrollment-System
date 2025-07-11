package vn.edu.iuh.fit.enrollservice.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.enrollservice.dtos.CheckoutClassRequest;
import vn.edu.iuh.fit.enrollservice.dtos.CheckoutMessage;
import vn.edu.iuh.fit.enrollservice.services.EnrollmentService;

import java.util.List;

@Service
public class CheckoutMessageConsumer {
    private final EnrollmentService enrollmentService;

    public CheckoutMessageConsumer(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @KafkaListener(topics = "checkout-topic", groupId = "enroll-service-group")
    public void receiveCheckout(CheckoutMessage message) {
        List<String> classIds = message.checkoutClassRequests().stream().map(CheckoutClassRequest::classId).toList();
        enrollmentService.updateEnrollmentStatus(message.studentId(), classIds, message.status());
    }
}