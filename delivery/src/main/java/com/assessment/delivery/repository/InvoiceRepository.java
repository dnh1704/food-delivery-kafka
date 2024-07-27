package com.assessment.delivery.repository;

import com.assessment.delivery.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Krishna Chaitanya
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
