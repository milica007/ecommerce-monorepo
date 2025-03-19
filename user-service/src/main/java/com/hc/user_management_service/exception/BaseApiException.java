package com.hc.user_management_service.exception;

import com.hc.user_management_service.dto.error.ErrorDTO;
import lombok.Getter;

@Getter
public class BaseApiException extends RuntimeException {

    private final ErrorDTO error;

    public BaseApiException(ErrorDTO error) {
        this.error = error;
    }

}
