package com.example.rmg.usecase.purchase.list;

import com.example.rmg.domain.course.entity.Course;
import com.example.rmg.domain.purchase.entity.Purchase;
import com.example.rmg.domain.purchase.persistence.PurchasePersistence;
import com.example.rmg.domain.user.entity.User;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.infrastructure.test.builders.Courses;
import com.example.rmg.infrastructure.test.builders.Purchases;
import com.example.rmg.usecase.purchase.common.output.PurchaseView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.rmg.infrastructure.test.builders.Users.anUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListPurchaseUseCaseTest {

    @InjectMocks
    private ListPurchaseUseCase useCase;

    @Mock
    private PurchasePersistence purchasePersistence;

    @Mock
    private UserPersistence userPersistence;


    @Test
    void should_list_purchases() {

        User buyer = anUser().build();

        when(userPersistence.get(any())).thenReturn(buyer);

        Purchase purchase1 = Purchases.aPurchase(buyer).build();
        Course course1 = Courses.aCourse().price(10.20).build();
        Course course2 = Courses.aCourse().price(22.20).build();
        purchase1.addCourse(course1);
        purchase1.addCourse(course2);


        Purchase purchase2 = Purchases.aPurchase(buyer).build();
        Course course3 = Courses.aCourse().price(11.25).build();
        Course course4 = Courses.aCourse().price(21.50).build();
        purchase1.addCourse(course3);
        purchase1.addCourse(course4);


        List<Purchase> purchases = List.of(purchase1, purchase2);
        when(purchasePersistence.findByBuyer(any())).thenReturn(purchases);

        ListPurchaseUseCaseInput input = ListPurchaseUseCaseInput.builder()
                .buyerId(buyer.getId())
                .build();

        ListPurchaseUseCaseOutput output = useCase.execute(input);


        for (int i = 0; i < output.getPurchases().size(); i++) {
            PurchaseView result = output.getPurchases().get(i);
            Purchase expected = purchases.get(i);
            assertEquals(expected.getBuyer().getId(), result.getBuyerId());
            assertEquals(expected.getTotal(), result.getTotal());
        }

    }

}