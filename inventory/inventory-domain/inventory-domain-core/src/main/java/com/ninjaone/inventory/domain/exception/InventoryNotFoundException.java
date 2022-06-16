package com.ninjaone.inventory.domain.exception;

import com.ninjaone.domain.exception.DomainException;

public class InventoryNotFoundException extends DomainException {
    public InventoryNotFoundException(String message) {
        super(message);
    }

    public InventoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
