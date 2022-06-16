package com.ninjaone.inventory.domain.event;

import com.ninjaone.domain.event.publisher.DomainEventPublisher;
import com.ninjaone.inventory.domain.entity.Device;

import java.time.ZonedDateTime;

public class AssociateServicesEvent extends DeviceEvent {

    private final DomainEventPublisher<AssociateServicesEvent> associateServicesEventDomainEventPublisher;


    public AssociateServicesEvent(Device device, ZonedDateTime createdAt, DomainEventPublisher<AssociateServicesEvent> associateServicesEventDomainEventPublisher) {
        super(device, createdAt);
        this.associateServicesEventDomainEventPublisher = associateServicesEventDomainEventPublisher;
    }


    @Override
    public void fire() {

        associateServicesEventDomainEventPublisher.publish(this);
    }
}
