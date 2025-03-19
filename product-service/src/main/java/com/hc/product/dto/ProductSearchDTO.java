package com.hc.product.dto;

import com.hc.product.model.Category;

import java.io.Serializable;
import java.util.List;

public record ProductSearchDTO(
        String name,
        String description,
        Category category,
        List<Long> ids
) implements Serializable {
}
