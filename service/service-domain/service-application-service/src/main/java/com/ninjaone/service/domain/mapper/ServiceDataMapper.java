package com.ninjaone.service.domain.mapper;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.domain.valueobject.ServiceId;
import com.ninjaone.service.domain.dto.command.service.ServiceCommand;
import com.ninjaone.service.domain.dto.command.service.ServiceResponse;
import com.ninjaone.service.domain.entity.Service;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

import static com.ninjaone.utils.NinjaStringUtils.defaultFormat;

@Component
public class ServiceDataMapper {

    public Service serviceCommandToService(ServiceCommand serviceCommand) {
        return Service.builder()
                .name(defaultFormat(serviceCommand.getName()))
                .description(defaultFormat(serviceCommand.getDescription()))
                .enabled(true)
                .build();
    }

    public Service serviceCommandToServiceWithId(UUID id, ServiceCommand serviceCommand) {
        return Service.builder()
                .serviceId(new ServiceId(id))
                .name(defaultFormat(serviceCommand.getName()))
                .description(defaultFormat(serviceCommand.getDescription()))
                .enabled(true)
                .build();
    }

    public ServiceResponse serviceToServiceResponse(Service service, String service_created_successfully) {
        return ServiceResponse.builder()
                .id(service.getId().getValue())
                .name(service.getName())
                .description(service.getDescription())
                .enabled(service.getEnabled())
                .createdDate(service.getCreatedDate())
                .modifiedDate(service.getModifiedDate())
                .message(service_created_successfully)
                .build();
    }

    public NinjaPage<ServiceResponse> serviceNinjaPageToServiceResponseNinjaPage(NinjaPage<Service> serviceNinjaPage) {
        return NinjaPage.builder()
                .page(serviceNinjaPage.getPage())
                .size(serviceNinjaPage.getSize())
                .totalElements(serviceNinjaPage.getTotalElements())
                .totalPages(serviceNinjaPage.getTotalPages())
                .content(serviceNinjaPage.getContent().stream().map(os -> {
                    return serviceToServiceResponse(os,"");
                }).collect(Collectors.toList()))
                .build();
    }
}
