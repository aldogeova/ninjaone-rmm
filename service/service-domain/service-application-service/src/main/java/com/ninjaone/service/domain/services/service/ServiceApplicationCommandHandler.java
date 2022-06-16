package com.ninjaone.service.domain.services.service;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.service.domain.dto.command.service.ServiceCommand;
import com.ninjaone.service.domain.dto.command.service.ServiceResponse;
import com.ninjaone.service.domain.entity.Service;
import com.ninjaone.service.domain.mapper.ServiceDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class ServiceApplicationCommandHandler {

    private final ServiceApplicationHelper serviceApplicationHelper;
    private final ServiceDataMapper serviceDataMapper;

    public ServiceApplicationCommandHandler(ServiceApplicationHelper serviceApplicationHelper,
                                            ServiceDataMapper serviceDataMapper) {
        this.serviceApplicationHelper = serviceApplicationHelper;
        this.serviceDataMapper = serviceDataMapper;
    }

    public ServiceResponse save(ServiceCommand serviceCommand) {
        Service service = serviceApplicationHelper.save(serviceCommand);
        log.info("Service is created with id {}", service.getId().getValue());
        return serviceDataMapper.serviceToServiceResponse(service, "Service created successfully");
    }

    public ServiceResponse update(UUID id, ServiceCommand serviceCommand) {
        Service service = serviceApplicationHelper.update(id, serviceCommand);
        log.info("Service is updated with id {}", service.getId().getValue());
        return serviceDataMapper.serviceToServiceResponse(service, "Service updated successfully");
    }

    public ServiceResponse delete(UUID id) {
        Service service = serviceApplicationHelper.delete(id);
        log.info("Service is deleted with id {}", service.getId().getValue());
        return serviceDataMapper.serviceToServiceResponse(service, "Service deleted successfully");
    }

    public NinjaPage<ServiceResponse> getAll(int page, int size) {
        NinjaPage<Service> serviceNinjaPage = serviceApplicationHelper.getAll(page, size);
        return serviceDataMapper.serviceNinjaPageToServiceResponseNinjaPage(serviceNinjaPage);
    }

    public ServiceResponse findById(UUID id) {
        Service service = serviceApplicationHelper.findById(id);
        return serviceDataMapper.serviceToServiceResponse(service, "Service found successfully");
    }
}
