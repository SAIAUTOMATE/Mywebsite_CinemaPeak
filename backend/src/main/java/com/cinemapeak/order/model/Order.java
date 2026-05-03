package com.cinemapeak.order.model;

import com.cinemapeak.order.dto.ShippingAddress;
import java.time.Instant;

public record Order(
    String orderId,
    String userId,
    String movieId,
    PurchaseType purchaseType,
    int price,
    OrderStatus status,
    String downloadLink,
    boolean streamingEnabled,
    ShippingAddress shippingAddress,
    Instant createdAt
) {}
