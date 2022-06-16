package com.ninjaone.service.domain.event;

import com.ninjaone.domain.event.DomainEvent;
import com.ninjaone.service.domain.entity.AssociateDeviceService;

import java.time.ZonedDateTime;
import java.util.List;

public abstract class ReviewDeviceServiceEvent implements DomainEvent<AssociateDeviceService> {

    private final AssociateDeviceService associateDeviceService;
    private final ZonedDateTime createdAt;
    private final List<String> failureMessages;

    public ReviewDeviceServiceEvent(AssociateDeviceService associateDeviceService, ZonedDateTime createdAt, List<String> failureMessages) {
        this.associateDeviceService = associateDeviceService;
        this.createdAt = createdAt;
        this.failureMessages = failureMessages;
    }

    public AssociateDeviceService getAssociateDeviceService() {
        return associateDeviceService;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }
}
