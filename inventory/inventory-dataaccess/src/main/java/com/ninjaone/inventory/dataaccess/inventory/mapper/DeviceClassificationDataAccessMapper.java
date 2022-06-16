package com.ninjaone.inventory.dataaccess.inventory.mapper;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.dataaccess.inventory.entity.DeviceClassificationEntity;
import com.ninjaone.inventory.dataaccess.inventory.entity.TypeDeviceEntity;
import com.ninjaone.inventory.domain.entity.DeviceClassification;
import com.ninjaone.inventory.domain.entity.OperativeSystem;
import com.ninjaone.inventory.domain.entity.TypeDevice;
import com.ninjaone.inventory.domain.valueobject.DeviceClassificationId;
import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class DeviceClassificationDataAccessMapper {

    public DeviceClassificationEntity deviceClassificationToDeviceClassificationEntity(DeviceClassification deviceClassification) {
        return DeviceClassificationEntity.builder()
                .id(deviceClassification.getId().getValue())
                .name(deviceClassification.getName())
                .description(deviceClassification.getDescription())
                .enabled(deviceClassification.getEnabled() != null ? deviceClassification.getEnabled() : true)
                .build();
    }

    public DeviceClassification deviceClassificationEntityToDeviceClassification(DeviceClassificationEntity deviceClassification) {
        return DeviceClassification.builder()
                .id(new DeviceClassificationId(deviceClassification.getId()))
                .name(deviceClassification.getName())
                .description(deviceClassification.getDescription())
                .enabled(deviceClassification.isEnabled())
                .lastModifiedDate(deviceClassification.getModifiedDate())
                .createdDate(deviceClassification.getCreatedDate())
                .build();
    }

    public NinjaPage<DeviceClassification> deviceClassificationEntityPageToDeviceClassificationNinjaPage(Page<DeviceClassificationEntity> entities) {
        return NinjaPage.builder()
                .totalElements(entities.getTotalElements())
                .totalPages(entities.getTotalPages())
                .page(entities.getNumber())
                .size(entities.getSize())
                .content(entities.getContent().stream().map(this::deviceClassificationEntityToDeviceClassification).toList())
                .build();
    }
}
