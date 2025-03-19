package com.hc.order.exception;

import com.hc.order.dto.error.ErrorDTO;
import lombok.Getter;

@Getter
public class BaseApiException extends RuntimeException {

    private final ErrorDTO error;

    public BaseApiException(ErrorDTO error) {
        this.error = error;
    }

}
