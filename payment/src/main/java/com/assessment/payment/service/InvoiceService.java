package com.assessment.payment.service;

import com.assessment.payment.dto.InvoiceResponse;
import com.assessment.payment.dto.OrderDetails;
import com.assessment.payment.model.Invoice;
import com.assessment.payment.repository.InvoiceRepository;
import lombok.*;
import org.springframework.stereotype.*;

/**
 * @author Krishna Chaitanya
 */
@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceResponse orderInvoice(final OrderDetails orderDetails) {
        var invoice = invoiceRepository.save(
                Invoice
                        .builder()
                        .orderId(orderDetails.orderId())
                        .invoiceAmount(orderDetails.billingAmount())
                        .build()
        );
        return new InvoiceResponse(invoice.getId(), invoice.getInvoiceAmount(), "successful");
    }
}
