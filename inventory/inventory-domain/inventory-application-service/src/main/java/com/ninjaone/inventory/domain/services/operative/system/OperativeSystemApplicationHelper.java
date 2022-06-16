package com.ninjaone.inventory.domain.services.operative.system;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.inventory.domain.dto.command.operative.system.OperativeSystemCommand;
import com.ninjaone.inventory.domain.entity.OperativeSystem;
import com.ninjaone.inventory.domain.exception.InventoryDomainException;
import com.ninjaone.inventory.domain.mapper.OperativeSystemDataMapper;
import com.ninjaone.inventory.domain.ports.output.repository.OperativeSystemRepository;
import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import static com.ninjaone.utils.NinjaStringUtils.defaultFormat;
import static com.ninjaone.utils.NinjaStringUtils.stripAccents;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OperativeSystemApplicationHelper {

    private final OperativeSystemRepository operativeSystemRepository;
    private final OperativeSystemDataMapper operativeSystemDataMapper;

    public OperativeSystemApplicationHelper(OperativeSystemRepository operativeSystemRepository,
                                 OperativeSystemDataMapper operativeSystemDataMapper) {
        this.operativeSystemRepository = operativeSystemRepository;
        this.operativeSystemDataMapper = operativeSystemDataMapper;
    }

    @Transactional
    public OperativeSystem save(OperativeSystemCommand operativeSystemCommand) {
        controlUnique(null, operativeSystemCommand.getName());
        OperativeSystem operativeSystem = operativeSystemDataMapper.operativeSystemCommandToOperativeSystem(operativeSystemCommand);
        operativeSystem.validate();
        operativeSystem.initialize();
        OperativeSystem operativeSystemResult = operativeSystemRepository.save(operativeSystem);
        if(operativeSystemResult == null) {
            throw new InventoryDomainException("OperativeSystem is not created");
        }
        return operativeSystemResult;
    }

    public OperativeSystem update(UUID id, OperativeSystemCommand operativeSystemCommand) {
        controlExistence(id);
        controlUnique(id, operativeSystemCommand.getName());
        OperativeSystem operativeSystem = operativeSystemDataMapper.operativeSystemCommandToOperativeSystemWithId(id, operativeSystemCommand);
        operativeSystem.validate();
        OperativeSystem operativeSystemResult = operativeSystemRepository.save(operativeSystem);
        if(operativeSystemResult == null) {
            throw new InventoryDomainException("OperativeSystem is not created");
        }
        return operativeSystemResult;
    }

    public OperativeSystem delete(UUID id) {
        controlExistence(id);
        OperativeSystem operativeSystem = operativeSystemRepository.findById(new OperativeSystemId(id)).get();
        operativeSystem.setEnabled(false);
        OperativeSystem operativeSystemResult = operativeSystemRepository.save(operativeSystem);
        return operativeSystemResult;
    }

    public NinjaPage<OperativeSystem> getAll(int page, int size) {
        return operativeSystemRepository.findAll(page, size);
    }

    public OperativeSystem findById(UUID id) {
        controlExistence(id);
        OperativeSystem operativeSystem = operativeSystemRepository.findById(new OperativeSystemId(id)).get();
        return operativeSystem;
    }

    /**
     * Control if the operative system name is unique
     * @param id
     * @param name
     */
    private void controlUnique(UUID id, String name) {
        Optional<OperativeSystem> operativeSystem;
        if(id == null){
            operativeSystem = operativeSystemRepository.findTopByName(stripAccents(defaultFormat(name)));
        }else{
            operativeSystem = operativeSystemRepository.findTopByNameAndIdNot(stripAccents(defaultFormat(name)), new OperativeSystemId(id));
        }
        if(operativeSystem.isPresent()) {
            throw new InventoryDomainException("OperativeSystem already exists");
        }
    }

    /**
     * Control if the operative system exists
     * @param id
     */
    public void controlExistence(UUID id) {
        Optional<OperativeSystem> operativeSystem = operativeSystemRepository.findById(new OperativeSystemId(id));
        if(!operativeSystem.isPresent()) {
            throw new InventoryDomainException("OperativeSystem with id "+id+" does not exist");
        }
    }


}
