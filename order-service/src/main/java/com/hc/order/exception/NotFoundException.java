package com.hc.order.exception;

import com.hc.order.dto.error.ErrorCode;
import com.hc.order.dto.error.ErrorDTO;

public class NotFoundException extends BaseApiException {

    public NotFoundException(String description) {
        super(new ErrorDTO(ErrorCode.NOT_FOUND, description));
    }
}
