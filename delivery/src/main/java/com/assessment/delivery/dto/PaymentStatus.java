package com.assessment.delivery.dto;

/**
 * @author Krishna Chaitanya
 */
public enum PaymentStatus {
    PENDING,
    PROCESSING,
    APPROVED,
    DELIVERED,
    CANCELLED;

    // We can have some of these classes as part of a shared library, so that these can be reused/referred.

}
