package com.ninjaone.domain.event;

public interface DomainEvent<T> {

    void fire();

}
