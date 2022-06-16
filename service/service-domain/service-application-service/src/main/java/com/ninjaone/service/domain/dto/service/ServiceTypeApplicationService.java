package com.ninjaone.service.domain.dto.service;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.service.domain.dto.command.service.type.ServiceTypeCommand;
import com.ninjaone.service.domain.dto.command.service.type.ServiceTypeResponse;

import java.util.UUID;

public interface ServiceTypeApplicationService {

    ServiceTypeResponse save(ServiceTypeCommand serviceTypeCommand);

    ServiceTypeResponse update(UUID id, ServiceTypeCommand serviceTypeCommand);

    ServiceTypeResponse delete(UUID id);

    NinjaPage<ServiceTypeResponse> getAll(int page, int size);

    ServiceTypeResponse findById(UUID uuid);

}
