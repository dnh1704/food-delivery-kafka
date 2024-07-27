package com.assessment.restaurant.service;

import com.assessment.restaurant.dto.*;
import com.assessment.restaurant.event.*;
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

}
