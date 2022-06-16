package com.ninjaone.service.domain.event;

import com.ninjaone.domain.event.publisher.DomainEventPublisher;
import com.ninjaone.service.domain.entity.AssociateDeviceService;

import java.time.ZonedDateTime;
import java.util.List;

public class IncorrectDeviceServiceEvent extends ReviewDeviceServiceEvent  {

    private final DomainEventPublisher<IncorrectDeviceServiceEvent> incorrectDeviceServiceEventDomainEventPublisher;

    public IncorrectDeviceServiceEvent(AssociateDeviceService associateDeviceService,
                                       ZonedDateTime createdAt,
                                       List<String> failureMessages, DomainEventPublisher<IncorrectDeviceServiceEvent> incorrectDeviceServiceEventDomainEventPublisher) {
        super(associateDeviceService, createdAt, failureMessages);
        this.incorrectDeviceServiceEventDomainEventPublisher = incorrectDeviceServiceEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        incorrectDeviceServiceEventDomainEventPublisher.publish(this);
    }
}
