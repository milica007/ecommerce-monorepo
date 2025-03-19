package com.hc.product.dto;

import com.hc.product.model.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record ProductCreateDTO(
        @NotNull String name,
        String description,
        Category category,
        Long stock
) {
}
