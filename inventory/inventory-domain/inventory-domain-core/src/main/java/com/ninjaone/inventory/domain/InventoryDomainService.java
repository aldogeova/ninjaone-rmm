package com.ninjaone.inventory.domain;

import com.ninjaone.domain.event.publisher.DomainEventPublisher;
import com.ninjaone.inventory.domain.entity.Customer;
import com.ninjaone.inventory.domain.entity.Device;
import com.ninjaone.inventory.domain.event.AssociateServicesEvent;

import java.util.List;

public interface InventoryDomainService {

    AssociateServicesEvent associateServices(Device device, DomainEventPublisher<AssociateServicesEvent> associateServicesEventDomainEventPublisher);

}
