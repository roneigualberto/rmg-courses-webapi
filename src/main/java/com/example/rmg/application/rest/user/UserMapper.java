package com.example.rmg.application.rest.user;

import com.example.rmg.usecase.paymentmethod.common.input.PaymentMethodForm;
import com.example.rmg.usecase.paymentmethod.common.output.PaymentMethodView;
import com.example.rmg.usecase.user.create.CreateUserUseCaseInput;
import com.example.rmg.usecase.user.create.common.output.UserView;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    CreateUserUseCaseInput toCreateUserUseCaseInput(UserRequest request);

    UserResponse toUserResponse(UserView userView);


    PaymentMethodForm toPaymentMethodForm(PaymentMethodRequest request);

    PaymentMethodResponse toPaymentMethodResponse(PaymentMethodView paymentMethodView);

    List<PaymentMethodResponse> toPaymentMethodResponseList(List<PaymentMethodView> paymentMethodViewList);

}
