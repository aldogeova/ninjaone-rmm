package com.ninjaone.inventory.domain.valueobject;

import com.ninjaone.domain.valueobject.BaseId;

import java.util.UUID;

public class OperativeSystemId extends BaseId<UUID> {

    public OperativeSystemId(UUID value) {
        super(value);
    }
}
