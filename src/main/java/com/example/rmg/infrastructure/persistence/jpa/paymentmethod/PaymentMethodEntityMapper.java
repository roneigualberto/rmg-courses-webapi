package com.example.rmg.infrastructure.persistence.jpa.paymentmethod;


import com.example.rmg.domain.paymentmethod.entity.PaymentMethod;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMethodEntityMapper {


    PaymentMethodEntity toPaymentMethodEntity(PaymentMethod paymentMethod);

    PaymentMethod toPaymentMethod(PaymentMethodEntity entity);
}
