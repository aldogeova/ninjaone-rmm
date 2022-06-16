package com.ninjaone.domain.event.publisher;

import com.ninjaone.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}
