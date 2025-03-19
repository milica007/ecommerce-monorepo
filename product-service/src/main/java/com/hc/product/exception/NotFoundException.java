package com.hc.product.exception;

import com.hc.product.dto.error.ErrorCode;
import com.hc.product.dto.error.ErrorDTO;

public class NotFoundException extends BaseApiException {

    public NotFoundException(String description) {
        super(new ErrorDTO(ErrorCode.NOT_FOUND, description));
    }
}
