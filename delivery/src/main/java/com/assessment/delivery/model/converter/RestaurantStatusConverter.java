package com.assessment.delivery.model.converter;

import com.assessment.delivery.dto.PaymentStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

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
