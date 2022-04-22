package com.example.rmg.application.rest.user;


import com.example.rmg.usecase.user.create.CreateUserUseCase;
import com.example.rmg.usecase.user.create.CreateUserUseCaseInput;
import com.example.rmg.usecase.user.create.CreateUserUseCaseOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {


    private final CreateUserUseCase createUserUseCase;

    private final UserMapper userMapper;


    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest request, UriComponentsBuilder uriBuilder) {

        final CreateUserUseCaseInput input = userMapper.toCreateUserUseCaseInput(request);
        final CreateUserUseCaseOutput output = createUserUseCase.execute(input);
        final UserResponse response = userMapper.toUserResponse(output.getUser());
        final URI location = uriBuilder.buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(location).body(response);
    }
}
