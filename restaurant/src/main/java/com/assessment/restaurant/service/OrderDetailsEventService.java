package com.assessment.restaurant.service;

import com.assessment.restaurant.dto.OrderStatusDetails;
import com.assessment.restaurant.dto.PaymentStatusDetails;
import com.assessment.restaurant.event.OrderStatusEventProducer;
//import com.assessment.restaurant.event.PaymentStatusDetailsKafkaProducer;
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
