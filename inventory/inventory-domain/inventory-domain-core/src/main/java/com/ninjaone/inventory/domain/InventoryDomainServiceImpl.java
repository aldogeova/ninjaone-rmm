package com.ninjaone.inventory.domain;

import com.ninjaone.domain.event.publisher.DomainEventPublisher;
import com.ninjaone.inventory.domain.entity.Customer;
import com.ninjaone.inventory.domain.entity.Device;
import com.ninjaone.inventory.domain.event.AssociateServicesEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class InventoryDomainServiceImpl implements InventoryDomainService{

    @Override
    public AssociateServicesEvent associateServices(Device device, DomainEventPublisher<AssociateServicesEvent> associateServicesEventDomainEventPublisher) {
        log.info("Device with id {} is associate services", device.getId());
        return new AssociateServicesEvent(device, ZonedDateTime.now(ZoneId.of("UTC")), associateServicesEventDomainEventPublisher);

    }


}
