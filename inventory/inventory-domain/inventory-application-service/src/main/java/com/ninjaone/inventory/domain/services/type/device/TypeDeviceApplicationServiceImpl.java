package com.ninjaone.inventory.domain.services.type.device;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.dto.command.type.device.TypeDeviceCommand;
import com.ninjaone.inventory.domain.dto.command.type.device.TypeDeviceResponse;
import com.ninjaone.inventory.domain.dto.service.TypeDeviceApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Validated
@Service
public class TypeDeviceApplicationServiceImpl implements TypeDeviceApplicationService {

    private final TypeDeviceApplicationCommandHandler typeDeviceApplicationCommandHandler;


    public TypeDeviceApplicationServiceImpl(TypeDeviceApplicationCommandHandler typeDeviceApplicationCommandHandler) {
        this.typeDeviceApplicationCommandHandler = typeDeviceApplicationCommandHandler;
    }

    @Override
    public TypeDeviceResponse save(TypeDeviceCommand typeDeviceCommand) {
        return typeDeviceApplicationCommandHandler.save(typeDeviceCommand);
    }

    @Override
    public TypeDeviceResponse update(UUID id, TypeDeviceCommand typeDeviceCommand) {
        return typeDeviceApplicationCommandHandler.update(id, typeDeviceCommand);
    }

    @Override
    public TypeDeviceResponse delete(UUID id) {
        return typeDeviceApplicationCommandHandler.delete(id);
    }

    @Override
    public NinjaPage<TypeDeviceResponse> getAll(int page, int size) {
        return typeDeviceApplicationCommandHandler.getAll(page, size);
    }

    @Override
    public TypeDeviceResponse findById(UUID uuid) {
        return typeDeviceApplicationCommandHandler.findById(uuid);
    }
}
