package com.assessment.delivery.event;

import com.assessment.delivery.dto.OrderStatusDetails;
import com.assessment.delivery.dto.PaymentStatusDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Krishna Chaitanya
 */
@Component
@Slf4j
public class OrderStatusDetailsKafkaProducer {

    private final KafkaTemplate<String, OrderStatusDetails> orderDetailsKafkaTemplate;

    @Value("${spring.kafka.order.status.topic.name:order-status}")
    private String topic;

    public OrderStatusDetailsKafkaProducer(KafkaTemplate<String, OrderStatusDetails> orderDetailsKafkaTemplate) {
        this.orderDetailsKafkaTemplate = orderDetailsKafkaTemplate;
        this.orderDetailsKafkaTemplate.setObservationEnabled(true);
    }

    public void writeToKafka(OrderStatusDetails orderStatusDetails) {
        orderDetailsKafkaTemplate
                .send(topic, String.valueOf(orderStatusDetails.orderId()), orderStatusDetails);
    }


}
