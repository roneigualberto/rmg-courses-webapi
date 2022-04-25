package com.example.rmg.usecase.paymentmethod.common.mappers;


import com.example.rmg.domain.paymentmethod.entity.PaymentMethod;
import com.example.rmg.usecase.paymentmethod.common.output.PaymentMethodView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMethodMapper {


    PaymentMethodMapper INSTANCE = Mappers.getMapper(PaymentMethodMapper.class);

    @Mapping(target = "ownerId", source = "owner.id")
    PaymentMethodView toPaymentMethodView(PaymentMethod paymentMethod);
}
