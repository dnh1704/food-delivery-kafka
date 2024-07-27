package com.assessment.payment.service;

import com.assessment.payment.dto.OrderStatusDetails;
import com.assessment.payment.dto.PaymentStatusDetails;
import com.assessment.payment.event.OrderStatusEventProducer;
import com.assessment.payment.event.PaymentStatusDetailsKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Krishna Chaitanya
 */
@Service
@RequiredArgsConstructor
public class OrderDetailsEventService {

    private final OrderStatusEventProducer orderStatusEventProducer;

    public void sendOrderStatusDetailsEvent(OrderStatusDetails orderStatusDetails) {
        orderStatusEventProducer.writeToKafka(orderStatusDetails);
    }
}
