package com.example.rmg.application.rest.user;


import com.example.rmg.usecase.paymentmethod.common.input.PaymentMethodForm;
import com.example.rmg.usecase.paymentmethod.create.CreatePaymentMethodUseCase;
import com.example.rmg.usecase.paymentmethod.create.CreatePaymentMethodUseCaseInput;
import com.example.rmg.usecase.paymentmethod.create.CreatePaymentMethodUseCaseOutput;
import com.example.rmg.usecase.paymentmethod.list.ListPaymentMethodUseCase;
import com.example.rmg.usecase.paymentmethod.list.ListPaymentMethodUseCaseInput;
import com.example.rmg.usecase.paymentmethod.list.ListPaymentMethodUseCaseOutput;
import com.example.rmg.usecase.purchase.common.input.PurchaseForm;
import com.example.rmg.usecase.purchase.list.ListPurchaseUseCase;
import com.example.rmg.usecase.purchase.list.ListPurchaseUseCaseInput;
import com.example.rmg.usecase.purchase.list.ListPurchaseUseCaseOutput;
import com.example.rmg.usecase.purchase.make.MakePurchaseUseCase;
import com.example.rmg.usecase.purchase.make.MakePurchaseUseCaseInput;
import com.example.rmg.usecase.purchase.make.MakePurchaseUseCaseOutput;
import com.example.rmg.usecase.subscription.completelecture.CompleteLectureUseCase;
import com.example.rmg.usecase.subscription.completelecture.CompleteLectureUseCaseInput;
import com.example.rmg.usecase.subscription.completelecture.CompleteLectureUseCaseOutput;
import com.example.rmg.usecase.subscription.list.ListSubscriptionUseCase;
import com.example.rmg.usecase.subscription.list.ListSubscriptionUseCaseInput;
import com.example.rmg.usecase.subscription.list.ListSubscriptionUseCaseOutput;
import com.example.rmg.usecase.subscription.undocompletelecture.UndoCompleteLectureUseCase;
import com.example.rmg.usecase.subscription.undocompletelecture.UndoCompleteLectureUseCaseInput;
import com.example.rmg.usecase.subscription.undocompletelecture.UndoCompleteLectureUseCaseOutput;
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

    private final MakePurchaseUseCase makePurchaseUseCase;

    private final ListPurchaseUseCase listPurchaseUseCase;

    private final ListSubscriptionUseCase listSubscriptionUseCase;

    private final CompleteLectureUseCase completeLectureUseCase;

    private final UndoCompleteLectureUseCase undoCompleteLectureUseCase;

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

    @PostMapping("{userId}/purchases")
    public ResponseEntity<PurchaseResponse> makePurchase(@PathVariable UUID userId, @RequestBody @Valid PurchaseRequest request, UriComponentsBuilder uriBuilder) {

        final PurchaseForm form = userMapper.toPurchaseForm(request);

        final MakePurchaseUseCaseInput input = MakePurchaseUseCaseInput.builder()
                .buyerId(userId)
                .purchase(form)
                .build();

        final MakePurchaseUseCaseOutput output = makePurchaseUseCase.execute(input);

        final PurchaseResponse response = userMapper.toPurchaseResponse(output.getPurchase());
        final URI location = uriBuilder.buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("{userId}/purchases")
    public ResponseEntity<List<PurchaseResponse>> getPurchases(@PathVariable UUID userId) {

        final ListPurchaseUseCaseInput input = ListPurchaseUseCaseInput.builder()
                .buyerId(userId)
                .build();

        final ListPurchaseUseCaseOutput output = listPurchaseUseCase.execute(input);

        final List<PurchaseResponse> response = userMapper.toPurchaseResponseList(output.getPurchases());

        return ResponseEntity.ok(response);
    }


    @GetMapping("{userId}/subscriptions")
    public ResponseEntity<List<SubscriptionResponse>> getSubscriptions(@PathVariable UUID userId) {

        final ListSubscriptionUseCaseInput input = ListSubscriptionUseCaseInput.builder()
                .studentId(userId)
                .build();

        final ListSubscriptionUseCaseOutput output = listSubscriptionUseCase.execute(input);

        final List<SubscriptionResponse> response = userMapper.toSubscriptionResponseList(output.getSubscriptions());

        return ResponseEntity.ok(response);
    }


    @PatchMapping("{userId}/subscriptions/{subscriptionId}/completed-lecture/{lectureId}")
    public ResponseEntity<?> completeLecture(@PathVariable UUID userId, @PathVariable UUID subscriptionId, @PathVariable UUID lectureId) {

        final CompleteLectureUseCaseInput input = CompleteLectureUseCaseInput.builder()
                .studentId(userId)
                .subscriptionId(subscriptionId)
                .lectureId(lectureId)
                .build();

        final CompleteLectureUseCaseOutput output = completeLectureUseCase.execute(input);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{userId}/subscriptions/{subscriptionId}/completed-lecture/{lectureId}")
    public ResponseEntity<?> undoCompleteLecture(@PathVariable UUID userId, @PathVariable UUID subscriptionId, @PathVariable UUID lectureId) {

        final UndoCompleteLectureUseCaseInput input = UndoCompleteLectureUseCaseInput.builder()
                .studentId(userId)
                .subscriptionId(subscriptionId)
                .lectureId(lectureId)
                .build();

        final UndoCompleteLectureUseCaseOutput output = undoCompleteLectureUseCase.execute(input);

        return ResponseEntity.noContent().build();
    }


}
