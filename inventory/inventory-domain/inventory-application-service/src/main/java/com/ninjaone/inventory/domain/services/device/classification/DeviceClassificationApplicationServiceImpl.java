package com.ninjaone.inventory.domain.services.device.classification;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.dto.command.device.classification.DeviceClassificationCommand;
import com.ninjaone.inventory.domain.dto.command.device.classification.DeviceClassificationResponse;
import com.ninjaone.inventory.domain.dto.service.DeviceClassificationApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Validated
@Service
public class DeviceClassificationApplicationServiceImpl implements DeviceClassificationApplicationService {

    private final DeviceClassificationApplicationCommandHandler deviceClassificationApplicationCommandHandler;


    public DeviceClassificationApplicationServiceImpl(DeviceClassificationApplicationCommandHandler deviceClassificationApplicationCommandHandler) {
        this.deviceClassificationApplicationCommandHandler = deviceClassificationApplicationCommandHandler;
    }

    @Override
    public DeviceClassificationResponse save(DeviceClassificationCommand deviceClassificationCommand) {
        return deviceClassificationApplicationCommandHandler.save(deviceClassificationCommand);
    }

    @Override
    public DeviceClassificationResponse update(UUID id, DeviceClassificationCommand deviceClassificationCommand) {
        return deviceClassificationApplicationCommandHandler.update(id, deviceClassificationCommand);
    }

    @Override
    public DeviceClassificationResponse delete(UUID id) {
        return deviceClassificationApplicationCommandHandler.delete(id);
    }

    @Override
    public NinjaPage<DeviceClassificationResponse> getAll(int page, int size) {
        return deviceClassificationApplicationCommandHandler.getAll(page, size);
    }

    @Override
    public DeviceClassificationResponse findById(UUID uuid) {
        return deviceClassificationApplicationCommandHandler.findById(uuid);
    }
}
