package com.ninjaone.domain.valueobject;

import java.util.UUID;

public class DeviceId extends BaseId<UUID> {

    public DeviceId(UUID value) {
        super(value);
    }

}
