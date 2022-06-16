package com.ninjaone.service.dataaccess.service.type.mapper;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.domain.valueobject.Money;
import com.ninjaone.domain.valueobject.ServiceTypeId;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.service.dataaccess.service.entity.ServiceEntity;
import com.ninjaone.service.dataaccess.service.mapper.ServiceDataAccessMapper;
import com.ninjaone.service.dataaccess.service.type.entity.ServiceTypeEntity;
import com.ninjaone.service.domain.entity.Service;
import com.ninjaone.service.domain.entity.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ServiceTypeDataAccessMapper {

    public final ServiceDataAccessMapper serviceDataAccessMapper;

    public ServiceTypeDataAccessMapper(ServiceDataAccessMapper serviceDataAccessMapper) {
        this.serviceDataAccessMapper = serviceDataAccessMapper;
    }

    public ServiceTypeEntity serviceTypeToServiceTypeEntity(ServiceType serviceType) {
        return ServiceTypeEntity.builder()
                .id(serviceType.getId().getValue())
                .price(serviceType.getPrice().getCost())
                .typeDeviceId(serviceType.getTypeDeviceId().getValue())
                .service(ServiceEntity.builder()
                        .id(serviceType.getService().getId().getValue())
                        .build())
                .enabled(serviceType.getEnabled())
                .build();
    }


    public ServiceType serviceTypeEntityToServiceType(ServiceTypeEntity serviceType) {
        return ServiceType.builder()
                .serviceTypeId(new ServiceTypeId(serviceType.getId()))
                .price(new Money(serviceType.getPrice()))
                .typeDeviceId(new TypeDeviceId(serviceType.getTypeDeviceId()))
                .service(serviceDataAccessMapper.serviceEntityToService(serviceType.getService()))
                .createdDate(serviceType.getCreatedDate())
                .modifiedDate(serviceType.getModifiedDate())
                .enabled(serviceType.isEnabled())
                .build();
    }


    public NinjaPage<ServiceType> serviceTypeEntityPageToServiceTypeNinjaPage(Page<ServiceTypeEntity> entities) {
        return NinjaPage.builder()
                .totalElements(entities.getTotalElements())
                .totalPages(entities.getTotalPages())
                .page(entities.getNumber())
                .size(entities.getSize())
                .content(entities.getContent().stream().map(this::serviceTypeEntityToServiceType).toList())
                .build();
    }

}
