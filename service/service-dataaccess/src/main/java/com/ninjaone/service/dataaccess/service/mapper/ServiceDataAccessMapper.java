package com.ninjaone.service.dataaccess.service.mapper;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.domain.valueobject.ServiceId;
import com.ninjaone.service.dataaccess.service.entity.ServiceEntity;
import com.ninjaone.service.domain.entity.Service;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ServiceDataAccessMapper {

    public ServiceEntity serviceToServiceEntity(Service service) {
        return ServiceEntity.builder()
                .id(service.getId().getValue())
                .name(service.getName())
                .description(service.getDescription())
                .enabled(service.getEnabled() != null ? service.getEnabled() : true)
                .build();
    }

    public Service serviceEntityToService(ServiceEntity service) {
        return Service.builder()
                .serviceId(new ServiceId(service.getId()))
                .name(service.getName())
                .description(service.getDescription())
                .enabled(service.isEnabled())
                .createdDate(service.getCreatedDate())
                .modifiedDate(service.getModifiedDate())
                .build();
    }


    public NinjaPage<Service> serviceEntityPageToServiceNinjaPage(Page<ServiceEntity> entities) {
        return NinjaPage.builder()
                .totalElements(entities.getTotalElements())
                .totalPages(entities.getTotalPages())
                .page(entities.getNumber())
                .size(entities.getSize())
                .content(entities.getContent().stream().map(this::serviceEntityToService).toList())
                .build();
    }

}
