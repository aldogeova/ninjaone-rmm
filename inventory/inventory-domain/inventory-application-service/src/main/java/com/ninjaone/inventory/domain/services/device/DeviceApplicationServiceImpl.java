package com.ninjaone.inventory.domain.services.device;

import com.ninjaone.inventory.domain.DeviceCreateCommandHandler;
import com.ninjaone.inventory.domain.dto.command.device.CreateDeviceCommand;
import com.ninjaone.inventory.domain.dto.command.device.DeviceResponse;
import com.ninjaone.inventory.domain.dto.command.device.DeviceSearchResponse;
import com.ninjaone.inventory.domain.dto.track.TrackDeviceQuery;
import com.ninjaone.inventory.domain.dto.track.TrackDeviceResponse;
import com.ninjaone.inventory.domain.dto.service.DeviceApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Validated
@Service
public class DeviceApplicationServiceImpl implements DeviceApplicationService {

    private final DeviceCreateCommandHandler deviceCreateCommandHandler;

    private final DeviceTrackCommandHandler deviceTrackCommandHandler;

    public DeviceApplicationServiceImpl(DeviceCreateCommandHandler deviceCreateCommandHandler, DeviceTrackCommandHandler deviceTrackCommandHandler) {
        this.deviceCreateCommandHandler = deviceCreateCommandHandler;
        this.deviceTrackCommandHandler = deviceTrackCommandHandler;
    }

    @Override
    public DeviceResponse createDevice(CreateDeviceCommand createDeviceCommand) {
        return deviceCreateCommandHandler.createDevice(createDeviceCommand);
    }

    @Override
    public DeviceResponse update(UUID deviceId, CreateDeviceCommand createDeviceCommand) {
        return deviceCreateCommandHandler.update(deviceId, createDeviceCommand);
    }

    @Override
    public TrackDeviceResponse trackDevice(TrackDeviceQuery trackDeviceQuery) {
        return deviceTrackCommandHandler.trackDevice(trackDeviceQuery);
    }

    @Override
    public DeviceSearchResponse find(UUID deviceId) {
        return deviceTrackCommandHandler.find(deviceId);
    }

    @Override
    public DeviceResponse delete(UUID deviceId) {
        return deviceTrackCommandHandler.delete(deviceId);
    }
}
