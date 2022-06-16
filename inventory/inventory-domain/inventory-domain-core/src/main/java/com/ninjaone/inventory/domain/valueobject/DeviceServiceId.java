package com.ninjaone.inventory.domain.valueobject;

import com.ninjaone.domain.valueobject.BaseId;

import java.util.UUID;

public class DeviceServiceId extends BaseId<UUID> {

    public DeviceServiceId(UUID value) {
        super(value);
    }
}
