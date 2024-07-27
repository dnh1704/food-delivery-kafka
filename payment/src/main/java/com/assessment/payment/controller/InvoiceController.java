package com.assessment.payment.controller;

import com.assessment.payment.dto.InvoiceResponse;
import com.assessment.payment.dto.OrderDetails;
import com.assessment.payment.service.InvoiceService;
import jakarta.validation.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

/**
 * @author Krishna Chaitanya
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<InvoiceResponse> orderInvoice(@Valid @RequestBody OrderDetails orderDetails) {
        return ResponseEntity
                .status(OK)
                .body(invoiceService.orderInvoice(orderDetails));
    }

}
