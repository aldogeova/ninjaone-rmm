package com.ninjaone.service.domain.exception;

import com.ninjaone.domain.exception.DomainException;

public class ServiceApplicationServiceException extends DomainException {

    public ServiceApplicationServiceException(String message) {
        super(message);
    }

    public ServiceApplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
