package com.assessment.delivery.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Krishna Chaitanya
 */
@Validated
public record RestaurantDetails(
        @Min(1) int orderId,
        @Min(1) int customerId,
        @Min(1) int restaurantId,
        @NotBlank String deliveryAddress,
        @Min(1) BigDecimal billingAmount,
        @Size(min = 1) List<Items> items) {
}
