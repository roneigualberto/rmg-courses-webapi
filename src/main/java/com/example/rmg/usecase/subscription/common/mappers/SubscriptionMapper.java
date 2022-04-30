package com.example.rmg.usecase.subscription.common.mappers;


import com.example.rmg.domain.subscription.entity.Subscription;
import com.example.rmg.usecase.subscription.common.output.SubscriptionView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriptionMapper {


    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);

    @Mapping(target = "studentId", source = "student.id")
    SubscriptionView toSubscriptionView(Subscription subscription);
}
