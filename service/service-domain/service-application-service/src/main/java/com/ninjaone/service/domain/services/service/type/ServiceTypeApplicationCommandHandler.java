package com.ninjaone.service.domain.services.service.type;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.service.domain.dto.command.service.type.ServiceTypeCommand;
import com.ninjaone.service.domain.dto.command.service.type.ServiceTypeResponse;
import com.ninjaone.service.domain.entity.ServiceType;
import com.ninjaone.service.domain.mapper.ServiceTypeDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class ServiceTypeApplicationCommandHandler {

    private final ServiceTypeApplicationHelper serviceTypeApplicationHelper;
    private final ServiceTypeDataMapper serviceTypeDataMapper;

    public ServiceTypeApplicationCommandHandler(ServiceTypeApplicationHelper serviceTypeApplicationHelper,
                                                ServiceTypeDataMapper serviceTypeDataMapper) {
        this.serviceTypeApplicationHelper = serviceTypeApplicationHelper;
        this.serviceTypeDataMapper = serviceTypeDataMapper;
    }

    public ServiceTypeResponse save(ServiceTypeCommand serviceTypeCommand) {
        ServiceType service = serviceTypeApplicationHelper.save(serviceTypeCommand);
        log.info("ServiceType is created with id {}", service.getId().getValue());
        return serviceTypeDataMapper.serviceTypeToServiceTypeResponse(service, "ServiceType created successfully");
    }

    public ServiceTypeResponse update(UUID id, ServiceTypeCommand serviceTypeCommand) {
        ServiceType service = serviceTypeApplicationHelper.update(id, serviceTypeCommand);
        log.info("ServiceType is updated with id {}", service.getId().getValue());
        return serviceTypeDataMapper.serviceTypeToServiceTypeResponse(service, "ServiceType updated successfully");
    }

    public ServiceTypeResponse delete(UUID id) {
        ServiceType service = serviceTypeApplicationHelper.delete(id);
        log.info("ServiceType is deleted with id {}", service.getId().getValue());
        return serviceTypeDataMapper.serviceTypeToServiceTypeResponse(service, "ServiceType deleted successfully");
    }

    public NinjaPage<ServiceTypeResponse> getAll(int page, int size) {
        NinjaPage<ServiceType> serviceNinjaPage = serviceTypeApplicationHelper.getAll(page, size);
        return serviceTypeDataMapper.serviceTypeNinjaPageToServiceTypeResponseNinjaPage(serviceNinjaPage);
    }

    public ServiceTypeResponse findById(UUID id) {
        ServiceType service = serviceTypeApplicationHelper.findById(id);
        return serviceTypeDataMapper.serviceTypeToServiceTypeResponse(service, "ServiceType found successfully");
    }
}
