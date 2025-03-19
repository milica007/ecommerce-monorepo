package com.hc.user_management_service.exception;


import com.hc.user_management_service.dto.error.ErrorCode;
import com.hc.user_management_service.dto.error.ErrorDTO;

public class NotFoundException extends BaseApiException {

    public NotFoundException(String description) {
        super(new ErrorDTO(ErrorCode.NOT_FOUND, description));
    }
}
