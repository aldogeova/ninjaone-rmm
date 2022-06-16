package com.ninjaone.service.domain.services.service;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.service.domain.dto.command.service.ServiceCommand;
import com.ninjaone.service.domain.dto.command.service.ServiceResponse;
import com.ninjaone.service.domain.dto.service.ServiceApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Validated
@Service
public class ServiceApplicationServiceImpl implements ServiceApplicationService {

    private final ServiceApplicationCommandHandler serviceApplicationCommandHandler;


    public ServiceApplicationServiceImpl(ServiceApplicationCommandHandler serviceApplicationCommandHandler) {
        this.serviceApplicationCommandHandler = serviceApplicationCommandHandler;
    }

    @Override
    public ServiceResponse save(ServiceCommand serviceCommand) {
        return serviceApplicationCommandHandler.save(serviceCommand);
    }

    @Override
    public ServiceResponse update(UUID id, ServiceCommand serviceCommand) {
        return serviceApplicationCommandHandler.update(id, serviceCommand);
    }

    @Override
    public ServiceResponse delete(UUID id) {
        return serviceApplicationCommandHandler.delete(id);
    }

    @Override
    public NinjaPage<ServiceResponse> getAll(int page, int size) {
        return serviceApplicationCommandHandler.getAll(page, size);
    }

    @Override
    public ServiceResponse findById(UUID uuid) {
        return serviceApplicationCommandHandler.findById(uuid);
    }
}
