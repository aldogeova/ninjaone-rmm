package com.ninjaone.inventory.domain.services.operative.system;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemCommand;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemResponse;
import com.ninjaone.inventory.domain.entity.OperativeSystem;
import com.ninjaone.inventory.domain.mapper.OperativeSystemDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class OperativeSystemApplicationCommandHandler {

    private final OperativeSystemApplicationHelper operativeSystemApplicationHelper;
    private final OperativeSystemDataMapper operativeSystemDataMapper;

    public OperativeSystemApplicationCommandHandler(OperativeSystemApplicationHelper operativeSystemApplicationHelper,
                                                    OperativeSystemDataMapper operativeSystemDataMapper) {
        this.operativeSystemApplicationHelper = operativeSystemApplicationHelper;
        this.operativeSystemDataMapper = operativeSystemDataMapper;
    }

    public OperativeSystemResponse save(OperativeSystemCommand operativeSystemCommand) {
        OperativeSystem operativeSystem = operativeSystemApplicationHelper.save(operativeSystemCommand);
        log.info("OperativeSystem is created with id {}", operativeSystem.getId().getValue());
        return operativeSystemDataMapper.operativeSystemToOperativeSystemResponse(operativeSystem, "OperativeSystem created successfully");
    }

    public OperativeSystemResponse update(UUID id, OperativeSystemCommand operativeSystemCommand) {
        OperativeSystem operativeSystem = operativeSystemApplicationHelper.update(id, operativeSystemCommand);
        log.info("OperativeSystem is updated with id {}", operativeSystem.getId().getValue());
        return operativeSystemDataMapper.operativeSystemToOperativeSystemResponse(operativeSystem, "OperativeSystem updated successfully");
    }

    public OperativeSystemResponse delete(UUID id) {
        OperativeSystem operativeSystem = operativeSystemApplicationHelper.delete(id);
        log.info("OperativeSystem is deleted with id {}", operativeSystem.getId().getValue());
        return operativeSystemDataMapper.operativeSystemToOperativeSystemResponse(operativeSystem, "OperativeSystem deleted successfully");
    }

    public NinjaPage<OperativeSystemResponse> getAll(int page, int size) {
        NinjaPage<OperativeSystem> operativeSystemNinjaPage = operativeSystemApplicationHelper.getAll(page, size);
        return operativeSystemDataMapper.operativeSystemNinjaPageToOperativeSystemResponseNinjaPage(operativeSystemNinjaPage);
    }

    public OperativeSystemResponse findById(UUID id) {
        OperativeSystem operativeSystem = operativeSystemApplicationHelper.findById(id);
        return operativeSystemDataMapper.operativeSystemToOperativeSystemResponse(operativeSystem, "OperativeSystem found successfully");
    }
}
