package com.assessment.delivery.event;

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
public class PaymentStatusDetailsKafkaProducer {

    private final KafkaTemplate<String, PaymentStatusDetails> orderDetailsKafkaTemplate;

    @Value("${spring.kafka.restaurant.status.topic.name:payment-status}")
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
