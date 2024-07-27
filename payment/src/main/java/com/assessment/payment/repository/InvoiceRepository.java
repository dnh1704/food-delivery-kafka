package com.assessment.payment.repository;

import com.assessment.payment.model.Invoice;
import org.springframework.data.jpa.repository.*;

/**
 * @author Krishna Chaitanya
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
