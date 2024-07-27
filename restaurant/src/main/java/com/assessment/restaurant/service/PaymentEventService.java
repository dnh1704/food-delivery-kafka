package com.assessment.restaurant.service;

import com.assessment.restaurant.dto.OrderStatusDetails;
import com.assessment.restaurant.dto.PaymentStatusDetails;
import com.assessment.restaurant.event.PaymentStatusDetailsKafkaProducer;
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
