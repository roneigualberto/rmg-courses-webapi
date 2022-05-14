package com.example.rmg.infrastructure.application.rest.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserResponse {


    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;

}
