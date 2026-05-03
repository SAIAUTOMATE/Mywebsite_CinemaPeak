package com.cinemapeak.order.dto;

import jakarta.validation.constraints.NotBlank;

public record ShippingAddress(
    @NotBlank String line1,
    @NotBlank String city,
    @NotBlank String pincode
) {}
