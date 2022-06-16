package com.ninjaone.service.domain.event;

import com.ninjaone.domain.event.publisher.DomainEventPublisher;
import com.ninjaone.service.domain.entity.AssociateDeviceService;

import java.time.ZonedDateTime;
import java.util.Collections;

public class AssociatedDeviceServiceEvent extends ReviewDeviceServiceEvent  {

    private final DomainEventPublisher<AssociatedDeviceServiceEvent> associatedDeviceServiceEventDomainEventPublisher;

    public AssociatedDeviceServiceEvent(AssociateDeviceService associateDeviceService,
                                        ZonedDateTime createdAt,
                                        DomainEventPublisher<AssociatedDeviceServiceEvent> associatedDeviceServiceEventDomainEventPublisher) {
        super(associateDeviceService, createdAt, Collections.emptyList());
        this.associatedDeviceServiceEventDomainEventPublisher = associatedDeviceServiceEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        associatedDeviceServiceEventDomainEventPublisher.publish(this);
    }
}
