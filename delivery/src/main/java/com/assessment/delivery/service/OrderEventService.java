package com.assessment.delivery.service;

import com.assessment.delivery.dto.OrderStatusDetails;
import com.assessment.delivery.event.OrderStatusDetailsKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Krishna Chaitanya
 */
@Service
@RequiredArgsConstructor
public class OrderEventService {

    private final OrderStatusDetailsKafkaProducer orderStatusDetailsKafkaProducer;

    public void sendOrderStatusDetailsEvent(OrderStatusDetails orderStatusDetails) {
        orderStatusDetailsKafkaProducer.writeToKafka(orderStatusDetails);
    }

}
