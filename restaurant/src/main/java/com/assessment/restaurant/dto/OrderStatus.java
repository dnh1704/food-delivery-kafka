package com.assessment.restaurant.dto;

/**
 * @author Krishna Chaitanya
 */
public enum OrderStatus {
    PENDING,
    PROCESSING,
    APPROVED,
    DELIVERED,
    CANCELLED,
    PAID,
    COOKED,
    // We can have some of these classes as part of a shared library, so that these can be reused/referred.

}
