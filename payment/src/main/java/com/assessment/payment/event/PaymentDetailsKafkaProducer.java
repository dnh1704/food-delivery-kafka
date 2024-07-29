package com.assessment.payment.event;

import com.assessment.payment.dto.PaymentDetails;
import com.assessment.payment.dto.PaymentStatusDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Krishna Chaitanya
 */
@Component
@Slf4j
public class PaymentDetailsKafkaProducer {

    private final KafkaTemplate<String, PaymentDetails> paymentDetailsKafkaTemplate;

    @Value("${spring.kafka.payment.details.topic.name:payment-details}")
    private String topic;

    public PaymentDetailsKafkaProducer(KafkaTemplate<String, PaymentDetails> paymentDetailsKafkaTemplate) {
        this.paymentDetailsKafkaTemplate = paymentDetailsKafkaTemplate;
        this.paymentDetailsKafkaTemplate.setObservationEnabled(true);
    }

    public void sendPaymentDetailsEvent(PaymentDetails paymentDetails) {
        paymentDetailsKafkaTemplate
                .send(topic, String.valueOf(paymentDetails.orderDetails().orderId()), paymentDetails);
    }
}
