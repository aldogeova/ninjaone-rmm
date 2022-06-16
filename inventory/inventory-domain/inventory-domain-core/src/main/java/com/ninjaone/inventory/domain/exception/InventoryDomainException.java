package com.ninjaone.inventory.domain.exception;

import com.ninjaone.domain.exception.DomainException;

public class InventoryDomainException extends DomainException {
    public InventoryDomainException(String message) {
        super(message);
    }

    public InventoryDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
