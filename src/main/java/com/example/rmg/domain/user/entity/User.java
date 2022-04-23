package com.example.rmg.domain.user.entity;


import com.example.rmg.domain.common.validator.ValidatorUtil;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Getter
@Builder
public class User {

    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @NotBlank(message = "Password is required")
    private String password;


    private LocalDate birthDate;

    public void valid() {
        ValidatorUtil.validate(this);
    }


}
