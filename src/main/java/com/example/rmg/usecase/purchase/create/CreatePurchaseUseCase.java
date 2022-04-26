package com.example.rmg.usecase.purchase.create;


import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.paymentmethod.entity.PaymentMethod;
import com.example.rmg.domain.paymentmethod.persistence.PaymentMethodPersistence;
import com.example.rmg.domain.purchase.entity.Purchase;
import com.example.rmg.domain.purchase.persistence.PurchasePersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.common.UseCase;
import com.example.rmg.usecase.purchase.common.input.PurchaseForm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreatePurchaseUseCase implements UseCase<CreatePurchaseUseCaseInput, CreatePurchaseUseCaseOutput> {

    private final PurchasePersistence purchasePersistence;

    private final UserPersistence userPersistence;

    private final PaymentMethodPersistence paymentMethodPersistence;

    private final CoursePersistence coursePersistence;

    @Override
    public CreatePurchaseUseCaseOutput execute(CreatePurchaseUseCaseInput input) {


        final PurchaseForm form = input.getPurchase();

        final User buyer = userPersistence.get(input.getBuyerId());

        final PaymentMethod paymentMethod = paymentMethodPersistence.get(input.getPaymentMethodId());

        final Purchase purchase = Purchase.builder()
                .buyer(buyer)
                .paymentMethod(paymentMethod)
                .build();

        form.getCoursesId().stream()
                .map(coursePersistence::get)
                .forEach(purchase::addCourse);

        purchasePersistence.save(purchase);

        return CreatePurchaseUseCaseOutput.of(purchase);
    }
}
