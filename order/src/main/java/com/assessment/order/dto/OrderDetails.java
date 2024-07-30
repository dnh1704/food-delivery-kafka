package com.assessment.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Krishna Chaitanya
 */
public record OrderDetails(
        @Min(1) int orderId,
        @Min(1) int customerId,
        @Min(1) int restaurantId,
        @Min(1) String deliveryAddress,
        @NotBlank BigDecimal billingAmount,
        @Size(min = 1) List<Items> items) {
}
