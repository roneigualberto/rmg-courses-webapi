package com.example.rmg.usecase.user.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class CreateUserUseCaseInput {


    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private LocalDate birthDate;

}
