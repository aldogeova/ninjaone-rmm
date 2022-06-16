package com.ninjaone.inventory.domain.event;

import com.ninjaone.domain.event.DomainEvent;
import com.ninjaone.inventory.domain.entity.Device;

import java.time.ZonedDateTime;

public abstract class DeviceEvent implements DomainEvent<Device> {

    private final Device device;
    private final ZonedDateTime createdAt;

    public DeviceEvent(Device device, ZonedDateTime createdAt) {
        this.device = device;
        this.createdAt = createdAt;
    }

    public Device getDevice() {
        return device;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

}
