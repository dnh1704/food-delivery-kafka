package com.assessment.payment.repository;

import com.assessment.payment.model.RestaurantOrder;
import org.springframework.data.jpa.repository.*;

/**
 * @author Krishna Chaitanya
 */
public interface RestaurantOrderRepository extends JpaRepository<RestaurantOrder, Integer> {
}
