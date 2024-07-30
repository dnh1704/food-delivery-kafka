package com.assessment.order.event;

import com.assessment.order.dto.OrderStatus;
import com.assessment.order.dto.OrderStatusDetails;
import com.assessment.order.dto.RestaurantStatusDetails;
import com.assessment.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Krishna Chaitanya
 */
@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class OrderStatusEventHandler {

    private final OrderRepository orderRepository;

    @KafkaListener(topics = "${spring.kafka.order.status.topic.name:order-status}", groupId = "order-status")
    public void handleRestaurantStatus(OrderStatusDetails orderStatusDetails) {
        log.info("Order Status Details : {}", orderStatusDetails);
        int recordsUpdated = orderRepository.updateOrderStatusById(
                orderStatusDetails.orderId(),
                OrderStatus.valueOf(orderStatusDetails.status())
        );
        log.info("Records updated : {}", recordsUpdated);
    }

}
