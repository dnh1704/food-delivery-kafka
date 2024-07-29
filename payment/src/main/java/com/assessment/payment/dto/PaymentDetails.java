package com.assessment.payment.dto;

/**
 * @author Krishna Chaitanya
 */
public record PaymentDetails(OrderDetails orderDetails, String status) {
}
