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

    @KafkaListener(topics = "${spring.kafka.payment.details.topic.name:payment-details}", groupId = "restaurant-details-101")
    public void handle(PaymentDetails paymentDetails) throws InterruptedException {
        log.info("received order details event : {}", paymentDetails.orderDetails());
        if(paymentDetails.orderDetails().restaurantId() >= 3){
            System.out.println("Nha hang khong phuc vu  " + paymentDetails.orderDetails() );
            restaurantEventService.sendRestaurantDetailsEvent(new RestaurantDetails(paymentDetails.orderDetails(), RestaurantStatus.CANCELLED.name()));
            OrderDetailsEventService.sendOrderStatusDetailsEvent(new OrderStatusDetails(paymentDetails.orderDetails().orderId(), OrderStatus.CANCELLED.name()));
            paymentEventService.sendPaymentStatusDetailsEvent(new PaymentStatusDetails(paymentDetails.orderDetails().orderId(),PaymentStatus.CANCELLED.name()));
            //send order cancel
            //send payment
        }else{
            System.out.println("Nha hang chuan bi thanh cong  " + paymentDetails.orderDetails() );
            var restaurantOrder = restaurantOrderRepository.save(
                    RestaurantOrder
                            .builder()
                            .orderId(paymentDetails.orderDetails().orderId())
                            .restaurantId(paymentDetails.orderDetails().restaurantId())
                            .billingAmount(paymentDetails.orderDetails().billingAmount())
                            .restaurantStatus(RestaurantStatus.PROCESSING)
                            .build()
            );

            var restaurantOrderItems = paymentDetails.orderDetails()
                    .items()
                    .stream()
                    .map(i -> RestaurantOrderItem.from(i, restaurantOrder.getId()))
                    .toList();
            restaurantOrderItemRepository.saveAll(restaurantOrderItems);

            restaurantOrder.setRestaurantStatus(RestaurantStatus.APPROVED);
            restaurantOrderRepository.save(restaurantOrder);
            OrderDetailsEventService.sendOrderStatusDetailsEvent(new OrderStatusDetails(paymentDetails.orderDetails().orderId(), OrderStatus.COOKED.name()));
            restaurantEventService.sendRestaurantDetailsEvent(new RestaurantDetails(paymentDetails.orderDetails(), RestaurantStatus.APPROVED.name()));
        }
    }
}
