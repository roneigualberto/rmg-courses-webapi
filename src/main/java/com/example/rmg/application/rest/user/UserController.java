package com.example.rmg.application.rest.user;


import com.example.rmg.usecase.paymentmethod.common.input.PaymentMethodForm;
import com.example.rmg.usecase.paymentmethod.create.CreatePaymentMethodUseCase;
import com.example.rmg.usecase.paymentmethod.create.CreatePaymentMethodUseCaseInput;
import com.example.rmg.usecase.paymentmethod.create.CreatePaymentMethodUseCaseOutput;
import com.example.rmg.usecase.paymentmethod.list.ListPaymentMethodUseCase;
import com.example.rmg.usecase.paymentmethod.list.ListPaymentMethodUseCaseInput;
import com.example.rmg.usecase.paymentmethod.list.ListPaymentMethodUseCaseOutput;
import com.example.rmg.usecase.user.create.CreateUserUseCase;
import com.example.rmg.usecase.user.create.CreateUserUseCaseInput;
import com.example.rmg.usecase.user.create.CreateUserUseCaseOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {


    private final CreateUserUseCase createUserUseCase;

    private final CreatePaymentMethodUseCase createPaymentMethodUseCase;

    private final ListPaymentMethodUseCase listPaymentMethodUseCase;

    private final UserMapper userMapper;


    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest request, UriComponentsBuilder uriBuilder) {

        final CreateUserUseCaseInput input = userMapper.toCreateUserUseCaseInput(request);
        final CreateUserUseCaseOutput output = createUserUseCase.execute(input);
        final UserResponse response = userMapper.toUserResponse(output.getUser());
        final URI location = uriBuilder.buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(location).body(response);
    }

    @PostMapping("{userId}/payment-methods")
    public ResponseEntity<PaymentMethodResponse> addPaymentMethod(@PathVariable UUID userId, @RequestBody @Valid PaymentMethodRequest request, UriComponentsBuilder uriBuilder) {

        final PaymentMethodForm form = userMapper.toPaymentMethodForm(request);
        final CreatePaymentMethodUseCaseInput input = CreatePaymentMethodUseCaseInput.builder()
                .ownerId(userId)
                .paymentMethod(form).build();
        final CreatePaymentMethodUseCaseOutput output = createPaymentMethodUseCase.execute(input);
        final PaymentMethodResponse response = userMapper.toPaymentMethodResponse(output.getPaymentMethod());
        final URI location = uriBuilder.buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("{userId}/payment-methods")
    public ResponseEntity<List<PaymentMethodResponse>> getPaymentMethods(@PathVariable UUID userId) {

        final ListPaymentMethodUseCaseInput input = ListPaymentMethodUseCaseInput.builder()
                .ownerId(userId)
                .build();

        final ListPaymentMethodUseCaseOutput output = listPaymentMethodUseCase.execute(input);

        final List<PaymentMethodResponse> response = userMapper.toPaymentMethodResponseList(output.getPaymentMethods());

        return ResponseEntity.ok(response);
    }


}
