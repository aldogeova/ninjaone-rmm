package com.ninjaone.inventory.domain.dto.service;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.dto.command.type.device.TypeDeviceCommand;
import com.ninjaone.inventory.domain.dto.command.type.device.TypeDeviceResponse;

import java.util.UUID;

public interface TypeDeviceApplicationService {

    TypeDeviceResponse save(TypeDeviceCommand typeDeviceCommand);

    TypeDeviceResponse update(UUID id, TypeDeviceCommand typeDeviceCommand);

    TypeDeviceResponse delete(UUID id);

    NinjaPage<TypeDeviceResponse> getAll(int page, int size);

    TypeDeviceResponse findById(UUID uuid);
}
