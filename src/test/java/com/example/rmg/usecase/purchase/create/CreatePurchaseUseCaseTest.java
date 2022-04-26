package com.example.rmg.usecase.purchase.create;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.paymentmethod.entity.PaymentMethod;
import com.example.rmg.domain.paymentmethod.persistence.PaymentMethodPersistence;
import com.example.rmg.domain.purchase.entity.Purchase;
import com.example.rmg.domain.purchase.persistence.PurchasePersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.infrastructure.test.builders.Courses;
import com.example.rmg.infrastructure.test.builders.PaymentMethods;
import com.example.rmg.usecase.paymentmethod.common.input.PaymentMethodForm;
import com.example.rmg.usecase.paymentmethod.common.output.PaymentMethodView;
import com.example.rmg.usecase.paymentmethod.create.CreatePaymentMethodUseCase;
import com.example.rmg.usecase.paymentmethod.create.CreatePaymentMethodUseCaseInput;
import com.example.rmg.usecase.paymentmethod.create.CreatePaymentMethodUseCaseOutput;
import com.example.rmg.usecase.purchase.common.input.PurchaseForm;
import com.example.rmg.usecase.purchase.common.output.PurchaseView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.rmg.infrastructure.test.builders.PaymentMethods.aPaymentMethod;
import static com.example.rmg.infrastructure.test.builders.PaymentMethods.aPaymentMethodForm;
import static com.example.rmg.infrastructure.test.builders.Users.anUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePurchaseUseCaseTest {


    @InjectMocks
    private CreatePurchaseUseCase useCase;

    @Mock
    private PurchasePersistence purchasePersistence;

    @Mock
    private UserPersistence userPersistence;

    @Mock
    private PaymentMethodPersistence paymentMethodPersistence;

    @Mock
    private CoursePersistence coursePersistence;


    @Test
    void should_create_purchase() {

        final User buyer = givenBuyer();
        final PaymentMethod paymentMethod = givenPaymentMethod(buyer);
        final List<Course> courses = givenCourses();
        final Set<UUID> coursesId = courses.stream().map(Course::getId).collect(Collectors.toSet());
        final PurchaseForm form = PurchaseForm.builder().coursesId(coursesId).build();
        final CreatePurchaseUseCaseInput input = CreatePurchaseUseCaseInput.builder()
                .buyerId(buyer.getId())
                .paymentMethodId(paymentMethod.getId())
                .purchase(form)
                .build();

        final CreatePurchaseUseCaseOutput output = useCase.execute(input);

        final PurchaseView purchaseView = output.getPurchase();

        assertNotNull(purchaseView.getId());
        assertEquals(buyer.getId(), purchaseView.getBuyerId());
        assertEquals(paymentMethod.getId(), purchaseView.getPaymentMethod().getId());
        assertEquals(3, purchaseView.getItems().size());
        assertEquals(63.0, purchaseView.getTotal());

        verify(purchasePersistence).save(any());

    }

    private PaymentMethod givenPaymentMethod(User buyer) {
        final PaymentMethod paymentMethod = aPaymentMethod(buyer).build();
        when(paymentMethodPersistence.get(any())).thenReturn(paymentMethod);
        return paymentMethod;
    }

    private User givenBuyer() {
        final User buyer = anUser().build();
        when(userPersistence.get(any())).thenReturn(buyer);
        return buyer;
    }

    private List<Course> givenCourses() {
        final Course course1 = Courses.aCourse().title("Course 1").price(20.10).build();
        final Course course2 = Courses.aCourse().title("Course 2").price(19.70).build();
        final Course course3 = Courses.aCourse().title("Course 3").price(23.20).build();

        final List<Course> courses = List.of(course1, course2, course3);

        courses.forEach((course) -> when(coursePersistence.get(course.getId())).thenReturn(course));
        return courses;
    }
}