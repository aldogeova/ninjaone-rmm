package com.ninjaone.service.domain.exception;

import com.ninjaone.domain.exception.DomainException;

public class ServiceDomainException extends DomainException {

    public ServiceDomainException(String message) {
        super(message);
    }

    public ServiceDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
