package com.example.rmg.infrastructure.config;


import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.course.persistence.LecturePersistence;
import com.example.rmg.domain.course.storage.StorageService;
import com.example.rmg.domain.paymentmethod.persistence.PaymentMethodPersistence;
import com.example.rmg.domain.purchase.event.PurchaseCreatedEventHandler;
import com.example.rmg.domain.purchase.persistence.PurchasePersistence;
import com.example.rmg.domain.subscription.persistence.CompletedLecturePersistence;
import com.example.rmg.domain.subscription.persistence.SubscriptionPersistence;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.category.create.CreateCategoryUseCase;
import com.example.rmg.usecase.category.create.DefaultCreateCategoryUseCase;
import com.example.rmg.usecase.category.delete.DeleteCategoryUseCase;
import com.example.rmg.usecase.category.find.FindCategoryUseCase;
import com.example.rmg.usecase.category.list.ListCategoryUseCase;
import com.example.rmg.usecase.category.update.UpdateCategoryUseCase;
import com.example.rmg.usecase.course.create.CreateCourseUseCase;
import com.example.rmg.usecase.course.delete.DeleteCourseUseCase;
import com.example.rmg.usecase.course.find.FindCourseUseCase;
import com.example.rmg.usecase.course.lecture.create.CreateLectureUseCase;
import com.example.rmg.usecase.course.lecture.list.ListLectureUseCase;
import com.example.rmg.usecase.course.lecture.retrieve.RetrieveLectureMediaUseCase;
import com.example.rmg.usecase.course.lecture.store.StoreLectureMediaUseCase;
import com.example.rmg.usecase.course.list.ListCourseUseCase;
import com.example.rmg.usecase.course.publish.PublishCourseUseCase;
import com.example.rmg.usecase.course.update.UpdateCourseUseCase;
import com.example.rmg.usecase.paymentmethod.create.CreatePaymentMethodUseCase;
import com.example.rmg.usecase.paymentmethod.list.ListPaymentMethodUseCase;
import com.example.rmg.usecase.purchase.list.ListPurchaseUseCase;
import com.example.rmg.usecase.purchase.make.MakePurchaseUseCase;
import com.example.rmg.usecase.subscription.completelecture.CompleteLectureUseCase;
import com.example.rmg.usecase.subscription.create.CreateSubscriptionUseCase;
import com.example.rmg.usecase.subscription.finish.FinishSubscriptionUseCase;
import com.example.rmg.usecase.subscription.list.ListSubscriptionUseCase;
import com.example.rmg.usecase.subscription.undocompletelecture.UndoCompleteLectureUseCase;
import com.example.rmg.usecase.user.create.CreateUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public CreateCategoryUseCase createCategoryUseCase(CategoryPersistence categoryPersistence) {
        return new DefaultCreateCategoryUseCase(categoryPersistence);
    }

    @Bean
    public ListCategoryUseCase listCategoryUseCase(CategoryPersistence categoryPersistence) {
        return new ListCategoryUseCase(categoryPersistence);
    }

    @Bean
    public FindCategoryUseCase findCategoryUseCase(CategoryPersistence categoryPersistence) {
        return new FindCategoryUseCase(categoryPersistence);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase(CategoryPersistence categoryPersistence) {
        return new UpdateCategoryUseCase(categoryPersistence);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase(CategoryPersistence categoryPersistence) {
        return new DeleteCategoryUseCase(categoryPersistence);
    }

    @Bean
    public CreateUserUseCase createUserUseCase(UserPersistence userPersistence) {
        return new CreateUserUseCase(userPersistence);
    }

    @Bean
    public CreateCourseUseCase createCourseUseCase(
            CoursePersistence coursePersistence,
            CategoryPersistence categoryPersistence,
            UserPersistence userPersistence) {
        return new CreateCourseUseCase(coursePersistence, userPersistence, categoryPersistence);
    }


    @Bean
    public ListCourseUseCase listCourseUseCase(
            CoursePersistence coursePersistence
    ) {
        return new ListCourseUseCase(coursePersistence);
    }


    @Bean
    public FindCourseUseCase findCourseUseCase(
            CoursePersistence coursePersistence
    ) {
        return new FindCourseUseCase(coursePersistence);
    }

    @Bean
    public UpdateCourseUseCase updateCourseUseCase(CoursePersistence coursePersistence,
                                                   CategoryPersistence categoryPersistence,
                                                   UserPersistence userPersistence) {
        return new UpdateCourseUseCase(coursePersistence, categoryPersistence, userPersistence);
    }

    @Bean
    public DeleteCourseUseCase deleteCourseUseCase(
            CoursePersistence coursePersistence
    ) {
        return new DeleteCourseUseCase(coursePersistence);
    }

    @Bean
    public PublishCourseUseCase publishCourseUseCase(
            CoursePersistence coursePersistence
    ) {
        return new PublishCourseUseCase(coursePersistence);
    }

    @Bean
    public CreateLectureUseCase createLectureUseCase(
            CoursePersistence coursePersistence,
            LecturePersistence lecturePersistence
    ) {
        return new CreateLectureUseCase(coursePersistence, lecturePersistence);
    }

    @Bean
    public ListLectureUseCase listLectureUseCase(
            CoursePersistence coursePersistence,
            LecturePersistence lecturePersistence
    ) {
        return new ListLectureUseCase(coursePersistence, lecturePersistence);
    }

    @Bean
    public StoreLectureMediaUseCase storeLectureMediaUseCase(
            CoursePersistence coursePersistence,
            LecturePersistence lecturePersistence,
            StorageService storageService
    ) {
        return new StoreLectureMediaUseCase(coursePersistence, lecturePersistence, storageService);
    }


    @Bean
    public RetrieveLectureMediaUseCase retrieveLectureMediaUseCase(
            CoursePersistence coursePersistence,
            LecturePersistence lecturePersistence,
            StorageService storageService
    ) {
        return new RetrieveLectureMediaUseCase(coursePersistence, lecturePersistence, storageService);
    }


    @Bean
    public CreatePaymentMethodUseCase createPaymentMethodUseCase(
            PaymentMethodPersistence paymentMethodPersistence,
            UserPersistence userPersistence
    ) {
        return new CreatePaymentMethodUseCase(paymentMethodPersistence, userPersistence);
    }

    @Bean
    public ListPaymentMethodUseCase listPaymentMethodUseCase(
            PaymentMethodPersistence paymentMethodPersistence,
            UserPersistence userPersistence
    ) {
        return new ListPaymentMethodUseCase(paymentMethodPersistence, userPersistence);
    }


    @Bean
    public MakePurchaseUseCase createPurchaseUseCase(
            PurchasePersistence purchasePersistence,
            UserPersistence userPersistence,
            PaymentMethodPersistence paymentMethodPersistence,
            CoursePersistence coursePersistence,
            PurchaseCreatedEventHandler handler

    ) {
        return new MakePurchaseUseCase(purchasePersistence,
                userPersistence,
                paymentMethodPersistence,
                coursePersistence,
                handler);
    }

    @Bean
    public ListPurchaseUseCase listPurchaseUseCase(
            PurchasePersistence purchasePersistence,
            UserPersistence userPersistence
    ) {
        return new ListPurchaseUseCase(purchasePersistence, userPersistence);
    }


    @Bean
    public CreateSubscriptionUseCase createSubscriptionUseCase(
            SubscriptionPersistence subscriptionPersistence,
            UserPersistence userPersistence,
            CoursePersistence coursePersistence
    ) {
        return new CreateSubscriptionUseCase(subscriptionPersistence, userPersistence, coursePersistence);
    }

    @Bean
    public ListSubscriptionUseCase listSubscriptionUseCase(
            SubscriptionPersistence subscriptionPersistence,
            UserPersistence userPersistence
    ) {
        return new ListSubscriptionUseCase(subscriptionPersistence, userPersistence);
    }


    @Bean
    public CompleteLectureUseCase completeLectureUseCase(
            CompletedLecturePersistence completedLecturePersistence,
            SubscriptionPersistence subscriptionPersistence,
            LecturePersistence lecturePersistence,
            UserPersistence userPersistence
    ) {
        return new CompleteLectureUseCase(completedLecturePersistence, subscriptionPersistence, lecturePersistence, userPersistence);
    }


    @Bean
    public UndoCompleteLectureUseCase undoCompleteLectureUseCase(
            CompletedLecturePersistence completedLecturePersistence,
            SubscriptionPersistence subscriptionPersistence,
            LecturePersistence lecturePersistence,
            UserPersistence userPersistence
    ) {
        return new UndoCompleteLectureUseCase(completedLecturePersistence, subscriptionPersistence, lecturePersistence, userPersistence);
    }

    @Bean
    public FinishSubscriptionUseCase finishSubscriptionUseCase(
            SubscriptionPersistence subscriptionPersistence,
            UserPersistence userPersistence
    ) {
        return new FinishSubscriptionUseCase(subscriptionPersistence, userPersistence);
    }


}
