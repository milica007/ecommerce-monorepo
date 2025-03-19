package com.hc.product.dto;

import com.hc.product.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Jacksonized
public record ProductUpdateDTO(
        String name,
        String description,
        Category category,
        Long stock
) implements Serializable {
}
