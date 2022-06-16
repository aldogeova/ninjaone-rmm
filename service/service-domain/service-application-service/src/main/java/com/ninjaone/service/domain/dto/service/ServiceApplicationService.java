package com.ninjaone.service.domain.dto.service;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.service.domain.dto.command.service.ServiceCommand;
import com.ninjaone.service.domain.dto.command.service.ServiceResponse;

import java.util.UUID;

public interface ServiceApplicationService {

    ServiceResponse save(ServiceCommand serviceCommand);

    ServiceResponse update(UUID id, ServiceCommand serviceCommand);

    ServiceResponse delete(UUID id);

    NinjaPage<ServiceResponse> getAll(int page, int size);

    ServiceResponse findById(UUID uuid);

}
