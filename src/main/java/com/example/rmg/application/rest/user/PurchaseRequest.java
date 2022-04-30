package com.example.rmg.application.rest.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PurchaseRequest {

    private Set<UUID> coursesId;

    private UUID paymentMethodId;
}
