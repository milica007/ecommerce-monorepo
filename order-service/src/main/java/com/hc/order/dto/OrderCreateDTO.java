package com.hc.order.dto;

import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

public record OrderCreateDTO(
        @NotEmpty(message = "Product IDs cannot be empty")
        List<Long> productIds
) implements Serializable {
}
