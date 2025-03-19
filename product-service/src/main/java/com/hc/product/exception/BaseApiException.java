package com.hc.product.exception;

import com.hc.product.dto.error.ErrorDTO;
import lombok.Getter;

@Getter
public class BaseApiException extends RuntimeException {

    private final ErrorDTO error;

    public BaseApiException(ErrorDTO error) {
        this.error = error;
    }

}
