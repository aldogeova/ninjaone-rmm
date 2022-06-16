package com.ninjaone.inventory.domain.services.type.device;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.inventory.domain.dto.command.type.device.TypeDeviceCommand;
import com.ninjaone.inventory.domain.entity.TypeDevice;
import com.ninjaone.inventory.domain.exception.InventoryDomainException;
import com.ninjaone.inventory.domain.mapper.TypeDeviceDataMapper;
import com.ninjaone.inventory.domain.ports.output.repository.TypeDeviceRepository;
import com.ninjaone.inventory.domain.services.device.classification.DeviceClassificationApplicationHelper;
import com.ninjaone.inventory.domain.services.operative.system.OperativeSystemApplicationHelper;
import com.ninjaone.inventory.domain.valueobject.DeviceClassificationId;
import com.ninjaone.inventory.domain.valueobject.OperativeSystemId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import static com.ninjaone.utils.NinjaUuiiUtils.identifierToUuid;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class TypeDeviceApplicationHelper {

    private final TypeDeviceRepository typeDeviceRepository;
    private final TypeDeviceDataMapper typeDeviceDataMapper;

    private final OperativeSystemApplicationHelper operativeSystemApplicationHelper;

    private final DeviceClassificationApplicationHelper deviceClassificationApplicationHelper;



    public TypeDeviceApplicationHelper(TypeDeviceRepository typeDeviceRepository,
                                       TypeDeviceDataMapper typeDeviceDataMapper,
                                       OperativeSystemApplicationHelper operativeSystemApplicationHelper,
                                       DeviceClassificationApplicationHelper deviceClassificationApplicationHelper) {
        this.typeDeviceRepository = typeDeviceRepository;
        this.typeDeviceDataMapper = typeDeviceDataMapper;
        this.operativeSystemApplicationHelper = operativeSystemApplicationHelper;
        this.deviceClassificationApplicationHelper = deviceClassificationApplicationHelper;
    }

    @Transactional
    public TypeDevice save(TypeDeviceCommand typeDeviceCommand) {
        checkExistenceFK(typeDeviceCommand);
        controlUnique(null, typeDeviceCommand);
        TypeDevice typeDevice = typeDeviceDataMapper.entityCommandToEntity(typeDeviceCommand);
        typeDevice.validate();
        typeDevice.initialize();
        TypeDevice typeDeviceResult = typeDeviceRepository.save(typeDevice);
        if(typeDeviceResult == null) {
            throw new InventoryDomainException("TypeDevice is not created");
        }
        return typeDeviceResult;
    }


    public TypeDevice update(UUID id, TypeDeviceCommand typeDeviceCommand) {
        controlExistence(id);
        controlUnique(id, typeDeviceCommand);
        TypeDevice typeDevice = typeDeviceDataMapper.entityCommandToEntityWithId(id, typeDeviceCommand);
        typeDevice.validate();
        TypeDevice typeDeviceResult = typeDeviceRepository.save(typeDevice);
        if(typeDeviceResult == null) {
            throw new InventoryDomainException("TypeDevice is not updated");
        }
        return typeDeviceResult;
    }

    public TypeDevice delete(UUID id) {
        controlExistence(id);
        TypeDevice typeDevice = typeDeviceRepository.findById(new TypeDeviceId(id)).get();
        typeDevice.setEnabled(false);
        TypeDevice typeDeviceResult = typeDeviceRepository.save(typeDevice);
        return typeDeviceResult;
    }

    public NinjaPage<TypeDevice> getAll(int page, int size) {
        return typeDeviceRepository.findAll(page, size);
    }

    public TypeDevice findById(UUID id) {
        controlExistence(id);
        TypeDevice typeDevice = typeDeviceRepository.findById(new TypeDeviceId(id)).get();
        return typeDevice;
    }

    /**
     * Check if type device and device classification exists
     */
    private void checkExistenceFK(TypeDeviceCommand typeDeviceCommand) {
        if(typeDeviceCommand.getOperativeSystemId() == null) {
            throw new InventoryDomainException("Operative system is required");
        }
        
        if(typeDeviceCommand.getDeviceClassificationId() == null) {
            throw new InventoryDomainException("Device classification is required");
        }

        operativeSystemApplicationHelper.controlExistence(identifierToUuid(typeDeviceCommand.getOperativeSystemId()));

        deviceClassificationApplicationHelper.controlExistence(identifierToUuid(typeDeviceCommand.getDeviceClassificationId()));
    }


    /**
     * Check if type device exists
     * @param id
     * @param typeDeviceCommand
     */
    private void controlUnique(UUID id, TypeDeviceCommand typeDeviceCommand) {
        Optional<TypeDevice> typeDevice;
        if(id == null){
            typeDevice = typeDeviceRepository.findByOperativeSystemIdAndDeviceClassificationId(
                    new OperativeSystemId(identifierToUuid(typeDeviceCommand.getOperativeSystemId())),
                    new DeviceClassificationId(identifierToUuid(typeDeviceCommand.getDeviceClassificationId())));
        }else{
            typeDevice = typeDeviceRepository.findTopByOperativeSystemIdAndDeviceClassificationIdAndIdNot(
                    new OperativeSystemId(identifierToUuid(typeDeviceCommand.getOperativeSystemId())),
                    new DeviceClassificationId(identifierToUuid(typeDeviceCommand.getDeviceClassificationId())), new TypeDeviceId(id)) ;
        }
        if(typeDevice.isPresent()) {
            throw new InventoryDomainException("TypeDevice already exists");
        }
    }

    /**
     * Control if the type device exists
     * @param id
     */
    public void controlExistence(UUID id) {
        Optional<TypeDevice> typeDevice = typeDeviceRepository.findById(new TypeDeviceId(id));
        if(!typeDevice.isPresent()) {
            throw new InventoryDomainException("TypeDevice with id "+id+" does not exist");
        }
    }


}
