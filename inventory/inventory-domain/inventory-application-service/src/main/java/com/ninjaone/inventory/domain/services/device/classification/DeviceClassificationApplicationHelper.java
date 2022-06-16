package com.ninjaone.inventory.domain.services.device.classification;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.dto.command.device.classification.DeviceClassificationCommand;
import com.ninjaone.inventory.domain.entity.DeviceClassification;
import com.ninjaone.inventory.domain.exception.InventoryDomainException;
import com.ninjaone.inventory.domain.mapper.DeviceClassificationDataMapper;
import com.ninjaone.inventory.domain.ports.output.repository.DeviceClassificationRepository;
import com.ninjaone.inventory.domain.valueobject.DeviceClassificationId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.ninjaone.utils.NinjaStringUtils.defaultFormat;
import static com.ninjaone.utils.NinjaStringUtils.stripAccents;

@Slf4j
@Component
public class DeviceClassificationApplicationHelper {

    private final DeviceClassificationRepository deviceClassificationRepository;
    private final DeviceClassificationDataMapper deviceClassificationDataMapper;

    public DeviceClassificationApplicationHelper(DeviceClassificationRepository deviceClassificationRepository,
                                                 DeviceClassificationDataMapper deviceClassificationDataMapper) {
        this.deviceClassificationRepository = deviceClassificationRepository;
        this.deviceClassificationDataMapper = deviceClassificationDataMapper;
    }

    @Transactional
    public DeviceClassification save(DeviceClassificationCommand deviceClassificationCommand) {
        controlUnique(null, deviceClassificationCommand.getName());
        DeviceClassification deviceClassification = deviceClassificationDataMapper.entityCommandToEntity(deviceClassificationCommand);
        deviceClassification.validate();
        deviceClassification.initialize();
        DeviceClassification deviceClassificationResult = deviceClassificationRepository.save(deviceClassification);
        if(deviceClassificationResult == null) {
            throw new InventoryDomainException("DeviceClassification is not created");
        }
        return deviceClassificationResult;
    }

    public DeviceClassification update(UUID id, DeviceClassificationCommand deviceClassificationCommand) {
        controlExistence(id);
        controlUnique(id, deviceClassificationCommand.getName());
        DeviceClassification deviceClassification = deviceClassificationDataMapper.entityCommandToEntityWithId(id, deviceClassificationCommand);
        deviceClassification.validate();
        DeviceClassification deviceClassificationResult = deviceClassificationRepository.save(deviceClassification);
        if(deviceClassificationResult == null) {
            throw new InventoryDomainException("DeviceClassification is not created");
        }
        return deviceClassificationResult;
    }

    public DeviceClassification delete(UUID id) {
        controlExistence(id);
        DeviceClassification deviceClassification = deviceClassificationRepository.findById(new DeviceClassificationId(id)).get();
        deviceClassification.setEnabled(false);
        DeviceClassification deviceClassificationResult = deviceClassificationRepository.save(deviceClassification);
        return deviceClassificationResult;
    }

    public NinjaPage<DeviceClassification> getAll(int page, int size) {
        return deviceClassificationRepository.findAll(page, size);
    }

    public DeviceClassification findById(UUID id) {
        controlExistence(id);
        DeviceClassification deviceClassification = deviceClassificationRepository.findById(new DeviceClassificationId(id)).get();
        return deviceClassification;
    }

    /**
     * Control if the device classification name is unique
     * @param id
     * @param name
     */
    private void controlUnique(UUID id, String name) {
        Optional<DeviceClassification> deviceClassification;
        if(id == null){
            deviceClassification = deviceClassificationRepository.findTopByName(stripAccents(defaultFormat(name)));
        }else{
            deviceClassification = deviceClassificationRepository.findTopByNameAndIdNot(stripAccents(defaultFormat(name)), new DeviceClassificationId(id));
        }
        if(deviceClassification.isPresent()) {
            throw new InventoryDomainException("DeviceClassification already exists");
        }
    }

    /**
     * Control if the device classification exists
     * @param id
     */
    public void controlExistence(UUID id) {
        Optional<DeviceClassification> deviceClassification = deviceClassificationRepository.findById(new DeviceClassificationId(id));
        if(!deviceClassification.isPresent()) {
            throw new InventoryDomainException("DeviceClassification with id "+id+" does not exist");
        }
    }


}
