package com.assessment.payment.repository;

import com.assessment.payment.model.RestaurantOrderItem;
import org.springframework.data.jpa.repository.*;

/**
 * @author Krishna Chaitanya
 */
public interface RestaurantOrderItemRepository extends JpaRepository<RestaurantOrderItem, Integer> {
}
