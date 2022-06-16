package com.ninjaone.inventory.domain.event;

import com.ninjaone.domain.event.DomainEvent;
import com.ninjaone.domain.event.publisher.DomainEventPublisher;
import com.ninjaone.inventory.domain.entity.Device;

import java.time.ZonedDateTime;

public class DeviceCreateEvent extends DeviceEvent {
    private final DomainEventPublisher<AssociateServicesEvent> associateServicesEventDomainEventPublisher;

    public DeviceCreateEvent(Device device, ZonedDateTime createdAt, DomainEventPublisher<AssociateServicesEvent> associateServicesEventDomainEventPublisher) {
        super(device, createdAt);
        this.associateServicesEventDomainEventPublisher = associateServicesEventDomainEventPublisher;
    }

    @Override
    public void fire() {

    }
}
