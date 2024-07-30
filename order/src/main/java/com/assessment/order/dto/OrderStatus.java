package com.assessment.order.dto;

/**
 * @author Krishna Chaitanya
 */
public enum OrderStatus {
    PENDING,
    PROCESSING,
    APPROVED,
    PAID,
    DELIVERED,
    CANCELLED,
    COOKED;

    // We can have some of these classes as part of a shared library, so that these can be reused/referred.

}
