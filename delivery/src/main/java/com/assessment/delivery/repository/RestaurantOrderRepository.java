package com.assessment.delivery.repository;

import com.assessment.delivery.model.RestaurantOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Krishna Chaitanya
 */
public interface RestaurantOrderRepository extends JpaRepository<RestaurantOrder, Integer> {
}
