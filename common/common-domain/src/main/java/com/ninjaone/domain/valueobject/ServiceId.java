package com.ninjaone.domain.valueobject;

import java.util.UUID;

public class ServiceId extends BaseId<UUID> {

    public ServiceId(UUID value) {
        super(value);
    }
}
