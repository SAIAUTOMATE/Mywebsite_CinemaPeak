package com.cinemapeak.order.dto;

import com.cinemapeak.order.model.PurchaseType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(
    @NotBlank String userId,
    @NotBlank String movieId,
    @NotNull PurchaseType purchaseType,
    @Valid ShippingAddress shippingAddress
) {}
