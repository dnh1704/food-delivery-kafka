package com.assessment.delivery.service;

import com.assessment.delivery.dto.PaymentStatusDetails;
import com.assessment.delivery.event.PaymentStatusDetailsKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
