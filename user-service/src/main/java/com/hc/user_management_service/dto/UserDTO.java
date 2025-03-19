package com.hc.user_management_service.dto;

import com.hc.user_management_service.model.Role;

import java.io.Serializable;
import java.util.Set;

public record UserDTO(
        Long id,
        String username,
        String email,
        Set<Role> roles
) implements Serializable {
}
