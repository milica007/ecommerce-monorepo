package com.hc.user_management_service.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record AuthDTO(
        String token
) implements Serializable {
}
