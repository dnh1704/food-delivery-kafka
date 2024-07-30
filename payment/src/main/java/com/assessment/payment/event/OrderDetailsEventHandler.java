package com.assessment.payment.event;

import com.assessment.payment.dto.*;
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

    private final PaymentDetailsKafkaProducer paymentDetailsKafkaProducer;
    private final OrderDetailsEventService orderDetailsEventService;

    @KafkaListener(topics = "${spring.kafka.order.details.topic.name:order-details}", groupId = "payment-consumer-101"
           )
    public void handle(OrderDetails orderDetails) throws InterruptedException {
        log.info("received order details event : {}", orderDetails);
        // Kiểm tra nếu tiền lớn hơn 1 triệu thì ko được thanh toán
        if (orderDetails.billingAmount().intValue() >= 30) {
            System.out.println("Thanh toan khong thanh cong: " + orderDetails);
            orderDetailsEventService.sendOrderStatusDetailsEvent(new OrderStatusDetails(orderDetails.orderId(), PaymentStatus.CANCELLED.name()));
        } else {
            System.out.println("Thanh toan thanh cong: " + orderDetails);
            paymentDetailsKafkaProducer.sendPaymentDetailsEvent(new PaymentDetails(orderDetails, PaymentStatus.PAID.name()));
            orderDetailsEventService.sendOrderStatusDetailsEvent(new OrderStatusDetails(orderDetails.orderId(), PaymentStatus.PAID.name()));
        }
    }
}
