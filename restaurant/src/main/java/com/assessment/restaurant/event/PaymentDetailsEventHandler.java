package com.assessment.restaurant.event;

import com.assessment.restaurant.dto.*;
import com.assessment.restaurant.model.*;
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
public class PaymentDetailsEventHandler {

    private final RestaurantOrderRepository restaurantOrderRepository;
    private final RestaurantOrderItemRepository restaurantOrderItemRepository;

    private final RestaurantEventService restaurantEventService;
    private final PaymentEventService paymentEventService;

    private final OrderDetailsEventService OrderDetailsEventService;

    @KafkaListener(topics = "${spring.kafka.payment.details.topic.name:payment-details}", groupId = "payment-details")
    public void handle(OrderDetails orderDetails) throws InterruptedException {
        log.info("received order details event : {}", orderDetails);
        if(orderDetails.restaurantId() >= 100){
            restaurantEventService.sendRestaurantDetailsEvent(new RestaurantDetails(orderDetails, RestaurantStatus.CANCELLED.name()));
            OrderDetailsEventService.sendOrderStatusDetailsEvent(new OrderStatusDetails(orderDetails, OrderStatus.CANCELLED.name()));
            paymentEventService.sendPaymentStatusDetailsEvent(new PaymentStatusDetails(orderDetails.orderId(),PaymentStatus.CANCELLED.name()));
            //send order cancel
            //send payment
        }else{
            var restaurantOrder = restaurantOrderRepository.save(
                    RestaurantOrder
                            .builder()
                            .orderId(orderDetails.orderId())
                            .restaurantId(orderDetails.restaurantId())
                            .billingAmount(orderDetails.billingAmount())
                            .restaurantStatus(RestaurantStatus.PROCESSING)
                            .build()
            );

            var restaurantOrderItems = orderDetails
                    .items()
                    .stream()
                    .map(i -> RestaurantOrderItem.from(i, restaurantOrder.getId()))
                    .toList();
            restaurantOrderItemRepository.saveAll(restaurantOrderItems);

            restaurantOrder.setRestaurantStatus(RestaurantStatus.APPROVED);
            restaurantOrderRepository.save(restaurantOrder);
            restaurantEventService.sendRestaurantDetailsEvent(new RestaurantDetails(orderDetails, RestaurantStatus.APPROVED.name()));
        }
    }
}
