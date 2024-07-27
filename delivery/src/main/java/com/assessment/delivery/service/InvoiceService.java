package com.assessment.delivery.service;

import com.assessment.delivery.dto.InvoiceResponse;
import com.assessment.delivery.dto.OrderDetails;
import com.assessment.delivery.model.Invoice;
import com.assessment.delivery.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
