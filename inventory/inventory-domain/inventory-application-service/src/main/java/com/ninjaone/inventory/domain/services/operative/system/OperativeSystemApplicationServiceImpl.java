package com.ninjaone.inventory.domain.services.operative.system;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemCommand;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemResponse;
import com.ninjaone.inventory.domain.dto.service.OperativeSystemApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Validated
@Service
public class OperativeSystemApplicationServiceImpl implements OperativeSystemApplicationService {

    private final OperativeSystemApplicationCommandHandler operativeSystemApplicationCommandHandler;


    public OperativeSystemApplicationServiceImpl(OperativeSystemApplicationCommandHandler operativeSystemApplicationCommandHandler) {
        this.operativeSystemApplicationCommandHandler = operativeSystemApplicationCommandHandler;
    }

    @Override
    public OperativeSystemResponse save(OperativeSystemCommand operativeSystemCommand) {
        return operativeSystemApplicationCommandHandler.save(operativeSystemCommand);
    }

    @Override
    public OperativeSystemResponse update(UUID id, OperativeSystemCommand operativeSystemCommand) {
        return operativeSystemApplicationCommandHandler.update(id, operativeSystemCommand);
    }

    @Override
    public OperativeSystemResponse delete(UUID id) {
        return operativeSystemApplicationCommandHandler.delete(id);
    }

    @Override
    public NinjaPage<OperativeSystemResponse> getAll(int page, int size) {
        return operativeSystemApplicationCommandHandler.getAll(page, size);
    }

    @Override
    public OperativeSystemResponse findById(UUID uuid) {
        return operativeSystemApplicationCommandHandler.findById(uuid);
    }
}
