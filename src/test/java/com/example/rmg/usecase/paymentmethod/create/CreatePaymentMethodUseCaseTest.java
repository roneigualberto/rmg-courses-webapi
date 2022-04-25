package com.example.rmg.usecase.paymentmethod.create;

import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.course.persistence.LecturePersistence;
import com.example.rmg.domain.paymentmethod.persistence.PaymentMethodPersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.infrastructure.test.builders.PaymentMethods;
import com.example.rmg.infrastructure.test.builders.Users;
import com.example.rmg.usecase.course.lecture.create.CreateLectureUseCase;
import com.example.rmg.usecase.paymentmethod.common.input.PaymentMethodForm;
import com.example.rmg.usecase.paymentmethod.common.output.PaymentMethodView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.rmg.infrastructure.test.builders.PaymentMethods.aPaymentMethodForm;
import static com.example.rmg.infrastructure.test.builders.Users.anUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CreatePaymentMethodUseCaseTest {

    @InjectMocks
    private CreatePaymentMethodUseCase useCase;

    @Mock
    private PaymentMethodPersistence paymentMethodPersistence;

    @Mock
    private UserPersistence userPersistence;

    @Test
    void should_create_payment_method() {

        User owner = anUser().build();

        when(userPersistence.get(any())).thenReturn(owner);

        PaymentMethodForm form = aPaymentMethodForm().build();

        CreatePaymentMethodUseCaseInput input = CreatePaymentMethodUseCaseInput.builder()
                .ownerId(owner.getId())
                .paymentMethod(form).build();

        CreatePaymentMethodUseCaseOutput output = useCase.execute(input);


        PaymentMethodView paymentMethodView = output.getPaymentMethod();

        assertNotNull(paymentMethodView.getId());
        assertEquals(form.getBrand(), paymentMethodView.getBrand());
        assertEquals(form.getCardNumber(), paymentMethodView.getCardNumber());
        assertEquals(form.getNameOnCard(), paymentMethodView.getNameOnCard());
        assertEquals(form.getExpirationMonth(), paymentMethodView.getExpirationMonth());
        assertEquals(form.getExpirationYear(), paymentMethodView.getExpirationYear());

        verify(paymentMethodPersistence).save(any());

    }
}