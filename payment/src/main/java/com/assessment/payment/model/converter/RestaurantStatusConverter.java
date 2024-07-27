package com.assessment.payment.model.converter;

import com.assessment.payment.dto.PaymentStatus;
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
