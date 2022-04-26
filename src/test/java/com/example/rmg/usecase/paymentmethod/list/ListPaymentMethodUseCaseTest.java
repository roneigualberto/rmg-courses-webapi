package com.example.rmg.usecase.paymentmethod.list;

import com.example.rmg.domain.paymentmethod.entity.PaymentMethod;
import com.example.rmg.domain.paymentmethod.persistence.PaymentMethodPersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.paymentmethod.common.output.PaymentMethodView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.rmg.infrastructure.test.builders.PaymentMethods.aPaymentMethod;
import static com.example.rmg.infrastructure.test.builders.Users.anUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListPaymentMethodUseCaseTest {

    @InjectMocks
    private ListPaymentMethodUseCase useCase;

    @Mock
    private PaymentMethodPersistence paymentMethodPersistence;

    @Mock
    private UserPersistence userPersistence;


    @Test
    void should_list_payment_methods() {

        User owner = anUser().build();

        when(userPersistence.get(any())).thenReturn(owner);

        final PaymentMethod paymentMethod1 = aPaymentMethod(owner).build();
        final PaymentMethod paymentMethod2 = aPaymentMethod(owner).build();
        final PaymentMethod paymentMethod3 = aPaymentMethod(owner).build();


        List<PaymentMethod> paymentMethods = List.of(paymentMethod1, paymentMethod2, paymentMethod3);
        when(paymentMethodPersistence.findByOwner(any())).thenReturn(paymentMethods);

        ListPaymentMethodUseCaseInput input = ListPaymentMethodUseCaseInput.builder()
                .ownerId(owner.getId())
                .build();

        ListPaymentMethodUseCaseOutput output = useCase.execute(input);


        for (int i = 0; i < output.getPaymentMethods().size(); i++) {
            PaymentMethodView paymentMethodResult = output.getPaymentMethods().get(i);
            PaymentMethod paymentMethodExpected = paymentMethods.get(i);
            assertEquals(paymentMethodExpected.getBrand(), paymentMethodResult.getBrand());
            assertEquals(paymentMethodExpected.getCardNumber(), paymentMethodResult.getCardNumber());
            assertEquals(paymentMethodExpected.getNameOnCard(), paymentMethodResult.getNameOnCard());
            assertEquals(paymentMethodExpected.getExpirationYear(), paymentMethodResult.getExpirationYear());
            assertEquals(paymentMethodExpected.getExpirationMonth(), paymentMethodResult.getExpirationMonth());
            assertEquals(paymentMethodExpected.getOwner().getId(), paymentMethodResult.getOwnerId());

        }

    }

}