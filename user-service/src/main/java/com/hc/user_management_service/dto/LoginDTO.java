package com.hc.user_management_service.dto;

import java.io.Serializable;

public record LoginDTO(
        String email,
        String password
) implements Serializable {
}
