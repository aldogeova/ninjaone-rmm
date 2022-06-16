package com.ninjaone.service.domain.ports.output.message.publisher;

import com.ninjaone.domain.event.publisher.DomainEventPublisher;
import com.ninjaone.service.domain.event.AssociatedDeviceServiceEvent;

public interface AssociatedDeviceServiceMessagePublisher extends DomainEventPublisher<AssociatedDeviceServiceEvent> {


}
