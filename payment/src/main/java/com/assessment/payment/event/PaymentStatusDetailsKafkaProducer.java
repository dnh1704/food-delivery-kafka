package com.assessment.payment.event;

import com.assessment.payment.dto.OrderStatusDetails;
import com.assessment.payment.dto.PaymentStatusDetails;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.*;

/**
 * @author Krishna Chaitanya
 */
@Component
@Slf4j
public class PaymentStatusDetailsKafkaProducer {

    private final KafkaTemplate<String, PaymentStatusDetails> orderDetailsKafkaTemplate;

    @Value("${spring.kafka.payment.status.topic.name:order-status}")
    private String topic;

    public PaymentStatusDetailsKafkaProducer(KafkaTemplate<String, PaymentStatusDetails> orderDetailsKafkaTemplate) {
        this.orderDetailsKafkaTemplate = orderDetailsKafkaTemplate;
        this.orderDetailsKafkaTemplate.setObservationEnabled(true);
    }

    public void writeToKafka(PaymentStatusDetails restaurantStatusDetails) {
        orderDetailsKafkaTemplate
                .send(topic, String.valueOf(restaurantStatusDetails.orderId()), restaurantStatusDetails);
    }
}
