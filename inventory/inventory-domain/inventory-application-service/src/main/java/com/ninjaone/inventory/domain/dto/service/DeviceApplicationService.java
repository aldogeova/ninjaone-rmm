package com.ninjaone.inventory.domain.dto.service;

import com.ninjaone.inventory.domain.dto.command.device.CreateDeviceCommand;
import com.ninjaone.inventory.domain.dto.command.device.DeviceResponse;
import com.ninjaone.inventory.domain.dto.command.device.DeviceSearchResponse;
import com.ninjaone.inventory.domain.dto.track.TrackDeviceQuery;
import com.ninjaone.inventory.domain.dto.track.TrackDeviceResponse;

import javax.validation.Valid;
import java.util.UUID;

public interface DeviceApplicationService {
    DeviceResponse createDevice(@Valid CreateDeviceCommand createDeviceCommand);

    DeviceResponse update(UUID deviceId, CreateDeviceCommand createDeviceCommand);

    TrackDeviceResponse trackDevice(@Valid TrackDeviceQuery trackDeviceQuery);

    DeviceSearchResponse find(UUID deviceId);

    DeviceResponse delete(UUID deviceId);
}
