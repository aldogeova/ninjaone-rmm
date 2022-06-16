package com.ninjaone.service.domain.mapper;

import com.ninjaone.domain.base.BasicResponse;
import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.domain.valueobject.Money;
import com.ninjaone.domain.valueobject.ServiceId;
import com.ninjaone.domain.valueobject.ServiceTypeId;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.service.domain.dto.command.service.type.ServiceTypeCommand;
import com.ninjaone.service.domain.dto.command.service.type.ServiceTypeResponse;
import com.ninjaone.service.domain.entity.Service;
import com.ninjaone.service.domain.entity.ServiceType;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

import static com.ninjaone.utils.NinjaStringUtils.defaultFormat;
import static com.ninjaone.utils.NinjaUuiiUtils.identifierToUuid;
@Component
public class ServiceTypeDataMapper {

    public ServiceType serviceTypeCommandToServiceType(ServiceTypeCommand serviceTypeCommand) {
        return ServiceType.builder()
                .price(new Money(serviceTypeCommand.getPrice()))
                .typeDeviceId(new TypeDeviceId(identifierToUuid(serviceTypeCommand.getTypeDeviceId())))
                .service(Service.builder()
                        .serviceId(new ServiceId(identifierToUuid(serviceTypeCommand.getServiceId())))
                        .build())
                .enabled(true)
                .build();
    }

    public ServiceType serviceTypeCommandToServiceTypeWithId(UUID id, ServiceTypeCommand serviceTypeCommand) {
        return ServiceType.builder()
                .serviceTypeId(new ServiceTypeId(id))
                .price(new Money(serviceTypeCommand.getPrice()))
                .typeDeviceId(new TypeDeviceId(identifierToUuid(serviceTypeCommand.getTypeDeviceId())))
                .service(Service.builder()
                        .serviceId(new ServiceId(identifierToUuid(serviceTypeCommand.getServiceId())))
                        .build())
                .enabled(true)
                .build();
    }

    public ServiceTypeResponse serviceTypeToServiceTypeResponse(ServiceType serviceType, String serviceType_created_successfully) {
        return ServiceTypeResponse.builder()
                .id(serviceType.getId().getValue())
                .typeDeviceId(serviceType.getTypeDeviceId().getValue())
                .service(BasicResponse.builder()
                        .id(serviceType.getService().getId().getValue())
                        .name(serviceType.getService().getName())
                        .build())
                .enabled(serviceType.getEnabled())
                .createdDate(serviceType.getCreatedDate())
                .modifiedDate(serviceType.getModifiedDate())
                .message(serviceType_created_successfully)
                .build();
    }

    public NinjaPage<ServiceTypeResponse> serviceTypeNinjaPageToServiceTypeResponseNinjaPage(NinjaPage<ServiceType> serviceTypeNinjaPage) {
        return NinjaPage.builder()
                .page(serviceTypeNinjaPage.getPage())
                .size(serviceTypeNinjaPage.getSize())
                .totalElements(serviceTypeNinjaPage.getTotalElements())
                .totalPages(serviceTypeNinjaPage.getTotalPages())
                .content(serviceTypeNinjaPage.getContent().stream().map(os -> {
                    return serviceTypeToServiceTypeResponse(os,"");
                }).collect(Collectors.toList()))
                .build();
    }
}
