package com.ninjaone.inventory.domain.dto.service;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemCommand;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemResponse;

import java.util.UUID;

public interface OperativeSystemApplicationService {

    OperativeSystemResponse save(OperativeSystemCommand operativeSystemCommand);

    OperativeSystemResponse update(UUID id, OperativeSystemCommand operativeSystemCommand);

    OperativeSystemResponse delete(UUID id);

    NinjaPage<OperativeSystemResponse> getAll(int page, int size);

    OperativeSystemResponse findById(UUID uuid);
}
