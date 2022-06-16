package com.ninjaone.inventory.domain.ports.output.message.publisher.service;

import com.ninjaone.domain.event.publisher.DomainEventPublisher;
import com.ninjaone.inventory.domain.event.AssociateServicesEvent;

public interface AssociateServiceMessagePublisher extends DomainEventPublisher<AssociateServicesEvent> {

}
