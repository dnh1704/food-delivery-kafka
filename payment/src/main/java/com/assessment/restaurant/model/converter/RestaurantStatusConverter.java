package com.assessment.restaurant.model.converter;

import com.assessment.restaurant.dto.*;
import jakarta.persistence.*;

/**
 * @author Krishna Chaitanya
 */
@Converter(autoApply = true)
public class RestaurantStatusConverter implements AttributeConverter<PaymentStatus, String> {

    @Override
    public String convertToDatabaseColumn(PaymentStatus orderStatus) {
        return orderStatus.name();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(String dbData) {
        return PaymentStatus.valueOf(dbData);
    }

}
