package com.ninjaone.inventory.domain.valueobject;

import com.ninjaone.domain.valueobject.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }

}
