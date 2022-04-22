package com.example.rmg.application.rest.user;

import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
