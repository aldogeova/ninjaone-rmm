package com.ninjaone.inventory.domain.mapper;

import com.ninjaone.domain.base.BasicResponse;
import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.inventory.domain.dto.command.type.device.TypeDeviceCommand;
import com.ninjaone.inventory.domain.dto.command.type.device.TypeDeviceResponse;
import com.ninjaone.inventory.domain.entity.DeviceClassification;
import com.ninjaone.inventory.domain.entity.OperativeSystem;
import com.ninjaone.inventory.domain.entity.TypeDevice;
import com.ninjaone.inventory.domain.valueobject.DeviceClassificationId;
import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;
import org.springframework.stereotype.Component;
import static com.ninjaone.utils.NinjaUuiiUtils.identifierToUuid;

import java.util.UUID;
import java.util.stream.Collectors;

import static com.ninjaone.utils.NinjaStringUtils.defaultFormat;

@Component
public class TypeDeviceDataMapper {

    public TypeDevice entityCommandToEntity(TypeDeviceCommand typeDeviceCommand) {
        return TypeDevice.builder()
                .description(defaultFormat(typeDeviceCommand.getDescription()))
                .enabled(true)
                .operativeSystem(OperativeSystem.builder().id(
                        new OperativeSystemId(identifierToUuid(typeDeviceCommand.getOperativeSystemId()))).build())
                .deviceClassification(DeviceClassification.builder().id(
                        new DeviceClassificationId(identifierToUuid(typeDeviceCommand.getDeviceClassificationId()))).build())
                .build();
    }

    public TypeDevice entityCommandToEntityWithId(UUID id, TypeDeviceCommand typeDeviceCommand) {
        return TypeDevice.builder()
                .id(new TypeDeviceId(id))
                .description(defaultFormat(typeDeviceCommand.getDescription()))
                .enabled(true)
                .operativeSystem(OperativeSystem.builder().id(
                        new OperativeSystemId(identifierToUuid(typeDeviceCommand.getOperativeSystemId()))).build())
                .deviceClassification(DeviceClassification.builder().id(
                        new DeviceClassificationId(identifierToUuid(typeDeviceCommand.getDeviceClassificationId()))).build())
                .build();
    }

    public TypeDeviceResponse entityToEntityResponse(TypeDevice typeDevice, String message) {
        return TypeDeviceResponse.builder()
                .id(typeDevice.getId().getValue())
                .description(typeDevice.getDescription())
                .operativeSystem(BasicResponse.builder()
                        .id(typeDevice.getOperativeSystem().getId().getValue())
                        .name(typeDevice.getOperativeSystem().getName())
                        .build())
                .deviceClassification(BasicResponse.builder()
                        .id(typeDevice.getDeviceClassification().getId().getValue())
                        .name(typeDevice.getDeviceClassification().getName())
                        .build())
                .enabled(typeDevice.getEnabled())
                .createdDate(typeDevice.getCreatedDate())
                .modifiedDate(typeDevice.getModifiedDate())
                .message(message)
                .build();
    }

    public NinjaPage<TypeDeviceResponse> entityNinjaPageToEntityResponseNinjaPage(NinjaPage<TypeDevice> typeDeviceNinjaPage) {
        return NinjaPage.builder()
                .page(typeDeviceNinjaPage.getPage())
                .size(typeDeviceNinjaPage.getSize())
                .totalElements(typeDeviceNinjaPage.getTotalElements())
                .totalPages(typeDeviceNinjaPage.getTotalPages())
                .content(typeDeviceNinjaPage.getContent().stream().map(item -> {
                    return entityToEntityResponse(item,"");
                }).collect(Collectors.toList()))
                .build();
    }
}
