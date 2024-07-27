package com.assessment.delivery.controller;

import com.assessment.delivery.dto.InvoiceResponse;
import com.assessment.delivery.dto.OrderDetails;
import com.assessment.delivery.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
