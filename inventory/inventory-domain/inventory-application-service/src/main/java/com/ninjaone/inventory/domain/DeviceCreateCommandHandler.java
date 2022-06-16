package com.ninjaone.inventory.domain;

import com.ninjaone.inventory.domain.dto.command.device.CreateDeviceCommand;
import com.ninjaone.inventory.domain.dto.command.device.DeviceResponse;
import com.ninjaone.inventory.domain.event.AssociateServicesEvent;
import com.ninjaone.inventory.domain.mapper.DeviceDataMapper;
import com.ninjaone.inventory.domain.ports.output.message.publisher.service.AssociateServiceMessagePublisher;
import com.ninjaone.inventory.domain.services.device.DeviceCreateHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class DeviceCreateCommandHandler {

    private final DeviceCreateHelper deviceCreateHelper;

    private final DeviceDataMapper deviceDataMapper;

    private AssociateServiceMessagePublisher associateServiceMessagePublisher;

    public DeviceCreateCommandHandler(DeviceCreateHelper deviceCreateHelper,
                                      DeviceDataMapper deviceDataMapper,
                                      AssociateServiceMessagePublisher associateServiceMessagePublisher) {
        this.deviceCreateHelper = deviceCreateHelper;
        this.deviceDataMapper = deviceDataMapper;
        this.associateServiceMessagePublisher = associateServiceMessagePublisher;
    }

    public DeviceResponse createDevice(CreateDeviceCommand createDeviceCommand){
        AssociateServicesEvent associateServiceEvent = deviceCreateHelper.persistDevice(createDeviceCommand);
        log.info("Device is created with id {}", associateServiceEvent.getDevice().getId().getValue());
        associateServiceMessagePublisher.publish(associateServiceEvent);
        return deviceDataMapper.deviceToCreateDeviceResponse(associateServiceEvent.getDevice(), "Device created successfully");
    }

    public DeviceResponse update(UUID deviceId, CreateDeviceCommand createDeviceCommand){
        AssociateServicesEvent associateServiceEvent = deviceCreateHelper.update(deviceId, createDeviceCommand);
        log.info("Device is updated with id {}", associateServiceEvent.getDevice().getId().getValue());
        associateServiceMessagePublisher.publish(associateServiceEvent);
        return deviceDataMapper.deviceToCreateDeviceResponse(associateServiceEvent.getDevice(), "Device updated successfully");
    }

}
