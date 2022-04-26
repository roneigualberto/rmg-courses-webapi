package com.example.rmg.usecase.purchase.common.mappers;


import com.example.rmg.domain.purchase.entity.Purchase;
import com.example.rmg.usecase.purchase.common.output.PurchaseView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PurchaseMapper {


    PurchaseMapper INSTANCE = Mappers.getMapper(PurchaseMapper.class);

    @Mapping(target = "buyerId", source = "buyer.id")
    PurchaseView toPurchaseView(Purchase purchase);
}
