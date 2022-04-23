package com.example.rmg.infrastructure.config;


import com.example.rmg.domain.category.persistence.CategoryPersistence;
import com.example.rmg.domain.course.persistence.CoursePersistence;
import com.example.rmg.domain.course.persistence.LecturePersistence;
import com.example.rmg.domain.user.persistence.UserPersistence;
import com.example.rmg.usecase.category.create.CreateCategoryUseCase;
import com.example.rmg.usecase.category.delete.DeleteCategoryUseCase;
import com.example.rmg.usecase.category.find.FindCategoryUseCase;
import com.example.rmg.usecase.category.list.ListCategoryUseCase;
import com.example.rmg.usecase.category.update.UpdateCategoryUseCase;
import com.example.rmg.usecase.course.create.CreateCourseUseCase;
import com.example.rmg.usecase.course.delete.DeleteCourseUseCase;
import com.example.rmg.usecase.course.find.FindCourseUseCase;
import com.example.rmg.usecase.course.lecture.create.CreateLectureUseCase;
import com.example.rmg.usecase.course.lecture.list.ListLectureUseCase;
import com.example.rmg.usecase.course.list.ListCourseUseCase;
import com.example.rmg.usecase.course.list.ListCourseUseCaseOutput;
import com.example.rmg.usecase.course.publish.PublishCourseUseCase;
import com.example.rmg.usecase.course.update.UpdateCourseUseCase;
import com.example.rmg.usecase.user.create.CreateUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public CreateCategoryUseCase createCategoryUseCase(CategoryPersistence categoryPersistence) {
        return new CreateCategoryUseCase(categoryPersistence);
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


}
