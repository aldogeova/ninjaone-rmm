package com.ninjaone.service.domain.exception;

import com.ninjaone.domain.exception.DomainException;

public class ServiceNotFoundException extends DomainException {

    public ServiceNotFoundException(String message) {
        super(message);
    }

    public ServiceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
