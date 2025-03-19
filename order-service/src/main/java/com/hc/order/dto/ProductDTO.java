package com.hc.order.dto;

import com.hc.order.model.Category;

import java.io.Serializable;

public record ProductDTO(
        Long id,
        String name,
        String description,
        Category category,
        Long stock
) implements Serializable {
}
