package com.assessment.payment.service;

import com.assessment.payment.dto.OrderStatusDetails;
import com.assessment.payment.dto.PaymentStatusDetails;
import com.assessment.payment.event.PaymentStatusDetailsKafkaProducer;
import lombok.*;
import org.springframework.stereotype.*;

/**
 * @author Krishna Chaitanya
 */
@Service
@RequiredArgsConstructor
public class PaymentEventService {

    private final PaymentStatusDetailsKafkaProducer paymentStatusDetailsKafkaProducer;

    public void sendPaymentStatusDetailsEvent(PaymentStatusDetails paymentStatusDetails) {
        paymentStatusDetailsKafkaProducer.writeToKafka(paymentStatusDetails);
    }

    public void sendOrderStatusDetailsEvent(PaymentStatusDetails paymentStatusDetails) {
        paymentStatusDetailsKafkaProducer.writeToKafka(paymentStatusDetails);
    }
}
