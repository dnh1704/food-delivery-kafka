package com.assessment.restaurant.event;

import com.assessment.restaurant.dto.OrderStatusDetails;
import com.assessment.restaurant.dto.PaymentStatusDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderStatusEventProducer {
    private final KafkaTemplate<String, OrderStatusDetails> orderDetailsKafkaTemplate;

    @Value("${spring.kafka.order.status.topic.name:order-status}")
    private String topic;

    public OrderStatusEventProducer(KafkaTemplate<String, OrderStatusDetails> orderDetailsKafkaTemplate) {
        this.orderDetailsKafkaTemplate = orderDetailsKafkaTemplate;
    }

    public void writeToKafka(OrderStatusDetails orderStatusDetails) {
        orderDetailsKafkaTemplate
                .send(topic, String.valueOf(orderStatusDetails.orderId()), orderStatusDetails);
    }
}
