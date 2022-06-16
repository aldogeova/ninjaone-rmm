package com.ninjaone.inventory.domain.services.type.device;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.dto.command.type.device.TypeDeviceCommand;
import com.ninjaone.inventory.domain.dto.command.type.device.TypeDeviceResponse;
import com.ninjaone.inventory.domain.entity.TypeDevice;
import com.ninjaone.inventory.domain.mapper.TypeDeviceDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class TypeDeviceApplicationCommandHandler {

    private final TypeDeviceApplicationHelper typeDeviceApplicationHelper;
    private final TypeDeviceDataMapper typeDeviceDataMapper;

    public TypeDeviceApplicationCommandHandler(TypeDeviceApplicationHelper typeDeviceApplicationHelper,
                                               TypeDeviceDataMapper typeDeviceDataMapper) {
        this.typeDeviceApplicationHelper = typeDeviceApplicationHelper;
        this.typeDeviceDataMapper = typeDeviceDataMapper;
    }

    public TypeDeviceResponse save(TypeDeviceCommand typeDeviceCommand) {
        TypeDevice typeDevice = typeDeviceApplicationHelper.save(typeDeviceCommand);
        log.info("TypeDevice is created with id {}", typeDevice.getId().getValue());
        return typeDeviceDataMapper.entityToEntityResponse(typeDevice, "TypeDevice created successfully");
    }

    public TypeDeviceResponse update(UUID id, TypeDeviceCommand typeDeviceCommand) {
        TypeDevice typeDevice = typeDeviceApplicationHelper.update(id, typeDeviceCommand);
        log.info("TypeDevice is updated with id {}", typeDevice.getId().getValue());
        return typeDeviceDataMapper.entityToEntityResponse(typeDevice, "TypeDevice updated successfully");
    }

    public TypeDeviceResponse delete(UUID id) {
        TypeDevice typeDevice = typeDeviceApplicationHelper.delete(id);
        log.info("TypeDevice is deleted with id {}", typeDevice.getId().getValue());
        return typeDeviceDataMapper.entityToEntityResponse(typeDevice, "TypeDevice deleted successfully");
    }

    public NinjaPage<TypeDeviceResponse> getAll(int page, int size) {
        NinjaPage<TypeDevice> typeDeviceNinjaPage = typeDeviceApplicationHelper.getAll(page, size);
        return typeDeviceDataMapper.entityNinjaPageToEntityResponseNinjaPage(typeDeviceNinjaPage);
    }

    public TypeDeviceResponse findById(UUID id) {
        TypeDevice typeDevice = typeDeviceApplicationHelper.findById(id);
        return typeDeviceDataMapper.entityToEntityResponse(typeDevice, "TypeDevice found successfully");
    }
}
