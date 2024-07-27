package com.assessment.restaurant.event;

import com.assessment.restaurant.dto.*;
import com.assessment.restaurant.repository.*;
import com.assessment.restaurant.service.*;
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

    @KafkaListener(topics = "${spring.kafka.order.details.topic.name:order-details}", groupId = "order-details")
    public void handle(OrderDetails orderDetails) throws InterruptedException {
        log.info("received order details event : {}", orderDetails);
        // Kiểm tra nếu tiền lớn hơn 1 triệu thì ko được thanh toán
        if(orderDetails.billingAmount().intValue() >= 1000000){
            paymentEventService.sendRestaurantStatusDetailsEvent(new PaymentStatusDetails(orderDetails.orderId(), PaymentStatus.CANCELLED.name()));
        }else{

            paymentEventService.sendRestaurantStatusDetailsEvent(new PaymentStatusDetails(orderDetails.orderId(), PaymentStatus.APPROVED.name()));
        }
    }
}
