package com.ninjaone.inventory.domain.services.device.classification;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.dto.command.device.classification.DeviceClassificationCommand;
import com.ninjaone.inventory.domain.dto.command.device.classification.DeviceClassificationResponse;
import com.ninjaone.inventory.domain.entity.DeviceClassification;
import com.ninjaone.inventory.domain.mapper.DeviceClassificationDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class DeviceClassificationApplicationCommandHandler {

    private final DeviceClassificationApplicationHelper deviceClassificationApplicationHelper;
    private final DeviceClassificationDataMapper deviceClassificationDataMapper;

    public DeviceClassificationApplicationCommandHandler(DeviceClassificationApplicationHelper deviceClassificationApplicationHelper,
                                                         DeviceClassificationDataMapper deviceClassificationDataMapper) {
        this.deviceClassificationApplicationHelper = deviceClassificationApplicationHelper;
        this.deviceClassificationDataMapper = deviceClassificationDataMapper;
    }

    public DeviceClassificationResponse save(DeviceClassificationCommand deviceClassificationCommand) {
        DeviceClassification deviceClassification = deviceClassificationApplicationHelper.save(deviceClassificationCommand);
        log.info("DeviceClassification is created with id {}", deviceClassification.getId().getValue());
        return deviceClassificationDataMapper.entityToEntityResponse(deviceClassification, "DeviceClassification created successfully");
    }

    public DeviceClassificationResponse update(UUID id, DeviceClassificationCommand deviceClassificationCommand) {
        DeviceClassification deviceClassification = deviceClassificationApplicationHelper.update(id, deviceClassificationCommand);
        log.info("DeviceClassification is updated with id {}", deviceClassification.getId().getValue());
        return deviceClassificationDataMapper.entityToEntityResponse(deviceClassification, "DeviceClassification updated successfully");
    }

    public DeviceClassificationResponse delete(UUID id) {
        DeviceClassification deviceClassification = deviceClassificationApplicationHelper.delete(id);
        log.info("DeviceClassification is deleted with id {}", deviceClassification.getId().getValue());
        return deviceClassificationDataMapper.entityToEntityResponse(deviceClassification, "DeviceClassification deleted successfully");
    }

    public NinjaPage<DeviceClassificationResponse> getAll(int page, int size) {
        NinjaPage<DeviceClassification> deviceClassificationNinjaPage = deviceClassificationApplicationHelper.getAll(page, size);
        return deviceClassificationDataMapper.entityNinjaPageToEntityResponseNinjaPage(deviceClassificationNinjaPage);
    }

    public DeviceClassificationResponse findById(UUID id) {
        DeviceClassification deviceClassification = deviceClassificationApplicationHelper.findById(id);
        return deviceClassificationDataMapper.entityToEntityResponse(deviceClassification, "DeviceClassification found successfully");
    }
}
