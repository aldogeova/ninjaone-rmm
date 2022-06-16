package com.ninjaone.inventory.domain.valueobject;

import com.ninjaone.domain.valueobject.BaseId;

import java.util.UUID;

public class DeviceClassificationId extends BaseId<UUID> {

    public DeviceClassificationId(UUID value) {
        super(value);
    }
}
