package com.ninjaone.service.domain.ports.output.message.publisher;

import com.ninjaone.domain.event.publisher.DomainEventPublisher;
import com.ninjaone.service.domain.event.AssociatedDeviceServiceEvent;
import com.ninjaone.service.domain.event.IncorrectDeviceServiceEvent;

public interface IncorrectDeviceServiceMessagePublisher extends DomainEventPublisher<IncorrectDeviceServiceEvent> {


}
