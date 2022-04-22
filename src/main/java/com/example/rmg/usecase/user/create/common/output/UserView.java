package com.example.rmg.usecase.user.create.common.output;

import com.example.rmg.domain.user.entity.User;
import com.example.rmg.usecase.category.common.ouput.CategoryView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;


@Data
@AllArgsConstructor
@Builder
public class UserView {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthDate;

    public static UserView of(User user) {
        return UserView.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .build();
    }

}
