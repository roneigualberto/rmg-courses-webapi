package com.example.rmg.usecase.purchase.make;


import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.paymentmethod.entity.PaymentMethod;
import com.example.rmg.domain.paymentmethod.persistence.PaymentMethodPersistence;
import com.example.rmg.domain.purchase.entity.Purchase;
import com.example.rmg.domain.purchase.event.PurchaseCreatedEvent;
import com.example.rmg.domain.purchase.event.PurchaseCreatedEventHandler;
import com.example.rmg.domain.purchase.persistence.PurchasePersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.common.UseCase;
import com.example.rmg.usecase.purchase.common.input.PurchaseForm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MakePurchaseUseCase implements UseCase<MakePurchaseUseCaseInput, MakePurchaseUseCaseOutput> {

    private final PurchasePersistence purchasePersistence;

    private final UserPersistence userPersistence;

    private final PaymentMethodPersistence paymentMethodPersistence;

    private final CoursePersistence coursePersistence;

    private final PurchaseCreatedEventHandler purchaseCreatedEventHandler;

    @Override
    public MakePurchaseUseCaseOutput execute(MakePurchaseUseCaseInput input) {


        final PurchaseForm form = input.getPurchase();

        final User buyer = userPersistence.get(input.getBuyerId());

        final PaymentMethod paymentMethod = paymentMethodPersistence.get(form.getPaymentMethodId());

        final Purchase purchase = Purchase.builder()
                .buyer(buyer)
                .paymentMethod(paymentMethod)
                .build();

        form.getCoursesId().stream()
                .map(coursePersistence::get)
                .forEach(purchase::addCourse);

        purchasePersistence.save(purchase);

        MakePurchaseUseCaseOutput output = MakePurchaseUseCaseOutput.of(purchase);

        purchaseCreatedEventHandler.handler(new PurchaseCreatedEvent(output.getPurchase()));

        return output;
    }
}
