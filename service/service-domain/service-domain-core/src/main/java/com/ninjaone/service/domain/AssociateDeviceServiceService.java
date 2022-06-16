package com.ninjaone.service.domain;

import com.ninjaone.domain.event.publisher.DomainEventPublisher;
import com.ninjaone.service.domain.entity.AssociateDeviceService;
import com.ninjaone.service.domain.event.AssociatedDeviceServiceEvent;
import com.ninjaone.service.domain.event.IncorrectDeviceServiceEvent;
import com.ninjaone.service.domain.event.ReviewDeviceServiceEvent;

import java.util.List;

public interface AssociateDeviceServiceService {

    ReviewDeviceServiceEvent review(AssociateDeviceService associateDeviceService,
                                    List<String> failureMessages,
                                    DomainEventPublisher<AssociatedDeviceServiceEvent> associatedDeviceServiceEventDomainEventPublisher,
                                    DomainEventPublisher<IncorrectDeviceServiceEvent> incorrectDeviceServiceEventDomainEventPublisher);
}
