package com.ninjaone.inventory.domain.dto.service;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.dto.command.device.classification.DeviceClassificationCommand;
import com.ninjaone.inventory.domain.dto.command.device.classification.DeviceClassificationResponse;

import java.util.UUID;

public interface DeviceClassificationApplicationService {

    DeviceClassificationResponse save(DeviceClassificationCommand DeviceClassificationCommand);

    DeviceClassificationResponse update(UUID id, DeviceClassificationCommand DeviceClassificationCommand);

    DeviceClassificationResponse delete(UUID id);

    NinjaPage<DeviceClassificationResponse> getAll(int page, int size);

    DeviceClassificationResponse findById(UUID uuid);
}
