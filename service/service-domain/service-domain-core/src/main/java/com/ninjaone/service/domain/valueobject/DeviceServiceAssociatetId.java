package com.ninjaone.service.domain.valueobject;

import com.ninjaone.domain.valueobject.BaseId;

import java.util.UUID;

public class DeviceServiceAssociatetId extends BaseId<UUID> {


    public DeviceServiceAssociatetId(UUID value) {
        super(value);
    }
}
