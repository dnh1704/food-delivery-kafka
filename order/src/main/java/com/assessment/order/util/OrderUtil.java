package com.assessment.order.util;

import com.assessment.order.dto.OrderDetails;
import com.assessment.order.dto.OrderRequest;
import com.assessment.order.model.Order;

/**
 * @author Krishna Chaitanya
 */
public final class OrderUtil {

    private OrderUtil() throws IllegalAccessException {
        throw new IllegalAccessException("can't be instantiated");
    }

    public static OrderDetails convertOrderRequestToOrderDetails(Order order, OrderRequest orderRequest) {
        return new OrderDetails(
                order.getId(),
                order.getCustomerId(),
                orderRequest.restaurantId(),
                order.getDeliveryAddress(),
                order.getBillingAmount(),
                orderRequest.items()
        );
    }
}
