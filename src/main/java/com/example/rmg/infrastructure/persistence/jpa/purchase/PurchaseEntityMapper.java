package com.example.rmg.infrastructure.persistence.jpa.purchase;


import com.example.rmg.domain.purchase.entity.Purchase;
import com.example.rmg.domain.purchase.entity.PurchaseItem;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseEntityMapper {

    PurchaseEntity toPurchaseEntity(Purchase purchase);

    Purchase toPurchase(PurchaseEntity purchaseEntity);

    @Mapping(target = "purchase", ignore = true)
    PurchaseItemEntity toPurchaseItemEntity(PurchaseItem purchaseItem);

    @Mapping(target = "purchase", ignore = true)
    PurchaseItem toPurchaseItem(PurchaseItemEntity purchaseItem);


    default Purchase toPurchaseWithItems(PurchaseEntity entity) {
        Purchase purchase = toPurchase(entity);
        purchase.getItems().forEach((item) -> item.setPurchase(purchase));
        return purchase;
    }

    default PurchaseEntity toPurchaseEntityWithItems(Purchase purchase) {
        PurchaseEntity purchaseEntity = toPurchaseEntity(purchase);
        purchaseEntity.getItems().forEach((item) -> item.setPurchase(purchaseEntity));
        return purchaseEntity;
    }


}
