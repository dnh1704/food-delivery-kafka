package com.assessment.delivery.event;

import com.assessment.delivery.dto.*;
import com.assessment.delivery.repository.RestaurantOrderItemRepository;
import com.assessment.delivery.repository.RestaurantOrderRepository;
import com.assessment.delivery.service.OrderEventService;
import com.assessment.delivery.service.PaymentEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Krishna Chaitanya
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RestaurantDetailsEventHandler {

    private final RestaurantOrderRepository restaurantOrderRepository;
    private final RestaurantOrderItemRepository restaurantOrderItemRepository;

    private final PaymentEventService paymentEventService;
    private final OrderEventService orderEventService;

    @KafkaListener(topics = "${spring.kafka.restaurant.details.topic.name:restaurant-details}", groupId = "restaurant-details")
    public void handle(OrderDetails orderDetails) throws InterruptedException {
        log.info("received restaurant details event : {}", orderDetails);
        // Kiểm tra nếu tiền lớn hơn 1 triệu thì ko được thanh toán
        if (!orderDetails.deliveryAddress().equals("HN")) {
            // rejcet lai cho pay
            paymentEventService.sendPaymentStatusDetailsEvent(new PaymentStatusDetails(orderDetails.orderId(), PaymentStatus.CANCELLED.name()));

            // rejcet lai cho order
            orderEventService.sendOrderStatusDetailsEvent(new OrderStatusDetails(orderDetails.orderId(), OrderStatus.CANCELLED.name()));
        } else {
            // giao don hang thanh cong thi bao order
            orderEventService.sendOrderStatusDetailsEvent(new OrderStatusDetails(orderDetails.orderId(), OrderStatus.DELIVERED.name()));
        }
    }
}
