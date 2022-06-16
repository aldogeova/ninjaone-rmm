package com.ninjaone.inventory.dataaccess.inventory.mapper;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.inventory.dataaccess.inventory.entity.DeviceEntity;
import com.ninjaone.inventory.dataaccess.inventory.entity.OperativeSystemEntity;
import com.ninjaone.inventory.dataaccess.inventory.entity.TypeDeviceEntity;
import com.ninjaone.inventory.domain.entity.Device;
import com.ninjaone.inventory.domain.entity.OperativeSystem;
import com.ninjaone.inventory.domain.entity.TypeDevice;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class TypeDeviceDataAccessMapper {

    private final DeviceClassificationDataAccessMapper deviceClassificationDataAccessMapper;
    private final OperativeSystemDataAccessMapper operativeSystemDataAccessMapper;

    public TypeDeviceDataAccessMapper(DeviceClassificationDataAccessMapper deviceClassificationDataAccessMapper, OperativeSystemDataAccessMapper operativeSystemDataAccessMapper) {
        this.deviceClassificationDataAccessMapper = deviceClassificationDataAccessMapper;
        this.operativeSystemDataAccessMapper = operativeSystemDataAccessMapper;
    }

    public TypeDeviceEntity typeDeviceToTypeDeviceEntity(TypeDevice typeDevice) {
        return TypeDeviceEntity.builder()
                .id(typeDevice.getId().getValue())
                .description(typeDevice.getDescription())
                .deviceClassification(deviceClassificationDataAccessMapper.deviceClassificationToDeviceClassificationEntity(typeDevice.getDeviceClassification()))
                .operativeSystem(operativeSystemDataAccessMapper.operativeSystemToOperativeSystemEntity(typeDevice.getOperativeSystem()))
                .enabled(typeDevice.getEnabled())
                .build();
    }

    public TypeDevice typeDeviceEntityToTypeDevice(TypeDeviceEntity typeDevice) {
        return TypeDevice.builder()
                .id(new TypeDeviceId(typeDevice.getId()))
                .description(typeDevice.getDescription())
                .deviceClassification(deviceClassificationDataAccessMapper.deviceClassificationEntityToDeviceClassification(typeDevice.getDeviceClassification()))
                .operativeSystem(operativeSystemDataAccessMapper.operativeSystemEntityToOperativeSystem(typeDevice.getOperativeSystem()))
                .enabled(typeDevice.isEnabled())
                .build();
    }

    public NinjaPage<TypeDevice> typeDeviceEntityPageToTypeDeviceNinjaPage(Page<TypeDeviceEntity> entities) {
        return NinjaPage.builder()
                .totalElements(entities.getTotalElements())
                .totalPages(entities.getTotalPages())
                .page(entities.getNumber())
                .size(entities.getSize())
                .content(entities.getContent().stream().map(this::typeDeviceEntityToTypeDevice).toList())
                .build();
    }

}
