package com.assessment.delivery.repository;

import com.assessment.delivery.model.RestaurantOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Krishna Chaitanya
 */
public interface RestaurantOrderItemRepository extends JpaRepository<RestaurantOrderItem, Integer> {
}
