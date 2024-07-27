package com.assessment.payment.event;

import com.assessment.payment.dto.OrderDetails;
import com.assessment.payment.dto.OrderStatusDetails;
import com.assessment.payment.dto.PaymentStatus;
import com.assessment.payment.dto.PaymentStatusDetails;
import com.assessment.payment.repository.RestaurantOrderItemRepository;
import com.assessment.payment.repository.RestaurantOrderRepository;
import com.assessment.payment.service.OrderDetailsEventService;
import com.assessment.payment.service.PaymentEventService;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.kafka.annotation.*;
import org.springframework.stereotype.*;

/**
 * @author Krishna Chaitanya
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrderDetailsEventHandler {

    private final RestaurantOrderRepository restaurantOrderRepository;
    private final RestaurantOrderItemRepository restaurantOrderItemRepository;

    private final PaymentEventService paymentEventService;
    private final OrderDetailsEventService orderDetailsEventService;

    @KafkaListener(topics = "${spring.kafka.order.details.topic.name:order-details}", groupId = "order-details")
    public void handle(OrderDetails orderDetails) throws InterruptedException {
        log.info("received order details event : {}", orderDetails);
        // Kiểm tra nếu tiền lớn hơn 1 triệu thì ko được thanh toán
        if (orderDetails.billingAmount().intValue() >= 1000000) {
            paymentEventService.sendPaymentStatusDetailsEvent(new PaymentStatusDetails(orderDetails.orderId(), PaymentStatus.CANCELLED.name()));
        } else {
            paymentEventService.sendOrderStatusDetailsEvent(new PaymentStatusDetails(orderDetails.orderId(), PaymentStatus.PAID.name()));
            orderDetailsEventService.sendOrderStatusDetailsEvent(new OrderStatusDetails(orderDetails, PaymentStatus.APPROVED.name()));
        }
    }
}