package com.ninjaone.service.domain.services.service.type;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.service.domain.dto.command.service.type.ServiceTypeCommand;
import com.ninjaone.service.domain.dto.command.service.type.ServiceTypeResponse;
import com.ninjaone.service.domain.dto.service.ServiceTypeApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Validated
@Service
public class ServiceTypeApplicationServiceImpl implements ServiceTypeApplicationService {

    private final ServiceTypeApplicationCommandHandler serviceTypeApplicationCommandHandler;


    public ServiceTypeApplicationServiceImpl(ServiceTypeApplicationCommandHandler serviceTypeApplicationCommandHandler) {
        this.serviceTypeApplicationCommandHandler = serviceTypeApplicationCommandHandler;
    }

    @Override
    public ServiceTypeResponse save(ServiceTypeCommand serviceTypeCommand) {
        return serviceTypeApplicationCommandHandler.save(serviceTypeCommand);
    }

    @Override
    public ServiceTypeResponse update(UUID id, ServiceTypeCommand serviceTypeCommand) {
        return serviceTypeApplicationCommandHandler.update(id, serviceTypeCommand);
    }

    @Override
    public ServiceTypeResponse delete(UUID id) {
        return serviceTypeApplicationCommandHandler.delete(id);
    }

    @Override
    public NinjaPage<ServiceTypeResponse> getAll(int page, int size) {
        return serviceTypeApplicationCommandHandler.getAll(page, size);
    }

    @Override
    public ServiceTypeResponse findById(UUID uuid) {
        return serviceTypeApplicationCommandHandler.findById(uuid);
    }
}
