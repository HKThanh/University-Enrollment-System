package vn.edu.iuh.fit.paymentservice.message;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.paymentservice.dtos.CheckoutClassRequest;
import vn.edu.iuh.fit.paymentservice.dtos.CheckoutMessage;
import vn.edu.iuh.fit.paymentservice.models.Invoice;
import vn.edu.iuh.fit.paymentservice.models.PaymentStatus;
import vn.edu.iuh.fit.paymentservice.services.InvoiceService;

import java.util.List;

@Service
public class CheckoutMessageProducer {
    private final InvoiceService invoiceService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public CheckoutMessageProducer(InvoiceService invoiceService, KafkaTemplate<String, Object> kafkaTemplate) {
        this.invoiceService = invoiceService;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCheckoutMessage(String studentId, String invoiceId, PaymentStatus status) {
        Invoice invoice = invoiceService.getInvoicesById(invoiceId);
        List<CheckoutClassRequest> checkoutClasses = invoice.getCoursePayments().stream()
                .map(coursePayment -> new CheckoutClassRequest(coursePayment.getClassId(), coursePayment.getCourseName()))
                .toList();

        // Send to checkout topic
        kafkaTemplate.send("checkout-topic", new CheckoutMessage(studentId, checkoutClasses, status));

        // Send to notification topic
        kafkaTemplate.send("checkout-notification-topic", new CheckoutMessage(studentId, checkoutClasses, status));
    }
}