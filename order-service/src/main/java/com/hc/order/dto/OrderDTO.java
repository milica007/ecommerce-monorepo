package com.hc.order.dto;

import java.util.List;

public record OrderDTO(
        Long id,
        UserDTO user,
        List<Long> productIds
) {
}
