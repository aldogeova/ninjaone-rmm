package com.ninjaone.inventory.domain.mapper;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.dto.command.device.classification.DeviceClassificationCommand;
import com.ninjaone.inventory.domain.dto.command.device.classification.DeviceClassificationResponse;
import com.ninjaone.inventory.domain.entity.DeviceClassification;
import com.ninjaone.inventory.domain.valueobject.DeviceClassificationId;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

import static com.ninjaone.utils.NinjaStringUtils.defaultFormat;

@Component
public class DeviceClassificationDataMapper {

    public DeviceClassification entityCommandToEntity(DeviceClassificationCommand deviceClassificationCommand) {
        return DeviceClassification.builder()
                .name(defaultFormat(deviceClassificationCommand.getName()))
                .description(defaultFormat(deviceClassificationCommand.getDescription()))
                .enabled(true)
                .build();
    }

    public DeviceClassification entityCommandToEntityWithId(UUID id, DeviceClassificationCommand deviceClassificationCommand) {
        return DeviceClassification.builder()
                .id(new DeviceClassificationId(id))
                .name(defaultFormat(deviceClassificationCommand.getName()))
                .description(defaultFormat(deviceClassificationCommand.getDescription()))
                .enabled(true)
                .build();
    }

    public DeviceClassificationResponse entityToEntityResponse(DeviceClassification operativeSystem, String operativeSystem_created_successfully) {
        return DeviceClassificationResponse.builder()
                .id(operativeSystem.getId().getValue())
                .name(operativeSystem.getName())
                .description(operativeSystem.getDescription())
                .enabled(operativeSystem.getEnabled())
                .createdDate(operativeSystem.getCreatedDate())
                .modifiedDate(operativeSystem.getModifiedDate())
                .message(operativeSystem_created_successfully)
                .build();
    }

    public NinjaPage<DeviceClassificationResponse> entityNinjaPageToEntityResponseNinjaPage(NinjaPage<DeviceClassification> operativeSystemNinjaPage) {
        return NinjaPage.builder()
                .page(operativeSystemNinjaPage.getPage())
                .size(operativeSystemNinjaPage.getSize())
                .totalElements(operativeSystemNinjaPage.getTotalElements())
                .totalPages(operativeSystemNinjaPage.getTotalPages())
                .content(operativeSystemNinjaPage.getContent().stream().map(os -> {
                    return entityToEntityResponse(os,"");
                }).collect(Collectors.toList()))
                .build();
    }
}
