package com.example.rmg.application.rest.user;

import com.example.rmg.usecase.paymentmethod.common.input.PaymentMethodForm;
import com.example.rmg.usecase.paymentmethod.common.output.PaymentMethodView;
import com.example.rmg.usecase.purchase.common.input.PurchaseForm;
import com.example.rmg.usecase.purchase.common.output.PurchaseView;
import com.example.rmg.usecase.subscription.common.output.SubscriptionView;
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

    PurchaseForm toPurchaseForm(PurchaseRequest request);

    PurchaseResponse toPurchaseResponse(PurchaseView purchaseView);

    List<PurchaseResponse> toPurchaseResponseList(List<PurchaseView> purchaseViewList);

    List<SubscriptionResponse> toSubscriptionResponseList(List<SubscriptionView> subscriptionViewList);


}
