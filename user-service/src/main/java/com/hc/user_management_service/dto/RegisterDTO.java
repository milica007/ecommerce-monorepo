package com.hc.user_management_service.dto;

import com.hc.user_management_service.model.RoleName;
import lombok.Builder;

import java.io.Serializable;
import java.util.Set;

@Builder
public record RegisterDTO(
        String username,
        String email,
        String password,
        Set<RoleName> roles
) implements Serializable {
}
