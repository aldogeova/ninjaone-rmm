package com.ninjaone.inventory.dataaccess.service.mapper;

import com.ninjaone.domain.valueobject.Money;
import com.ninjaone.domain.valueobject.ServiceTypeId;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.inventory.dataaccess.service.entity.ServiceTypeEntity;
import com.ninjaone.inventory.domain.entity.DeviceClassification;
import com.ninjaone.inventory.domain.entity.OperativeSystem;
import com.ninjaone.inventory.domain.entity.ServiceType;
import com.ninjaone.inventory.domain.entity.TypeDevice;
import com.ninjaone.inventory.domain.valueobject.DeviceClassificationId;
import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ServiceTypeDataAccessMapper {

    public ServiceType serviceTypeEntityToServiceType(ServiceTypeEntity serviceTypeEntity) {
        return  ServiceType.builder()
                .id(new ServiceTypeId(serviceTypeEntity.getServiceTypeId()))
                .price(new Money(serviceTypeEntity.getPrice()))
                .typeDevice(TypeDevice.builder()
                        .id(new TypeDeviceId(serviceTypeEntity.getTypeDeviceId()))
                        .description(serviceTypeEntity.getTypeDeviceDescription())
                        .deviceClassification(DeviceClassification.builder()
                                .id(new DeviceClassificationId(serviceTypeEntity.getIdDeviceClassification()))
                                .name(serviceTypeEntity.getNameDeviceClassification())
                                .build())
                        .operativeSystem(OperativeSystem.builder()
                                .id(new OperativeSystemId(serviceTypeEntity.getIdOperativeSystem()))
                                .name(serviceTypeEntity.getNameOperativeSystem())
                                .build())
                                .build()
                        ).build();

    }

    public List<ServiceType> serviceTypeEntitiesListToServiceTypeList(List<ServiceTypeEntity> serviceTypeEntityList) {
        return serviceTypeEntityList.stream()
                .map(this::serviceTypeEntityToServiceType)
                .collect(java.util.stream.Collectors.toList());
    }

}
