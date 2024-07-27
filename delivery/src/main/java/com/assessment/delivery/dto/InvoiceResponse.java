package com.assessment.delivery.dto;

import java.math.*;

/**
 * @author Krishna Chaitanya
 */
public record InvoiceResponse(int invoiceId, BigDecimal invoiceAmount, String message) {
}
