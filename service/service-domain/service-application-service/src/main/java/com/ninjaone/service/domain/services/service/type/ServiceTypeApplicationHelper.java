package com.ninjaone.service.domain.services.service.type;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.domain.valueobject.ServiceId;
import com.ninjaone.domain.valueobject.ServiceTypeId;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.service.domain.dto.command.service.type.ServiceTypeCommand;
import com.ninjaone.service.domain.entity.ServiceType;
import com.ninjaone.service.domain.entity.TypeDeviceView;
import com.ninjaone.service.domain.exception.ServiceDomainException;
import com.ninjaone.service.domain.mapper.ServiceTypeDataMapper;
import com.ninjaone.service.domain.ports.output.repository.ServiceTypeRepository;
import com.ninjaone.service.domain.ports.output.repository.TypeDeviceViewRepository;
import com.ninjaone.service.domain.services.service.ServiceApplicationHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.ninjaone.utils.NinjaUuiiUtils.identifierToUuid;

@Slf4j
@Component
public class ServiceTypeApplicationHelper {

    private final ServiceTypeRepository serviceTypeRepository;
    private final ServiceTypeDataMapper serviceTypeDataMapper;

    private final ServiceApplicationHelper serviceApplicationHelper;

    private final TypeDeviceViewRepository typeDeviceViewRepository;

    public ServiceTypeApplicationHelper(ServiceTypeRepository serviceTypeRepository,
                                        ServiceTypeDataMapper serviceTypeDataMapper,
                                        ServiceApplicationHelper serviceApplicationHelper,
                                        TypeDeviceViewRepository typeDeviceViewRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
        this.serviceTypeDataMapper = serviceTypeDataMapper;
        this.serviceApplicationHelper = serviceApplicationHelper;
        this.typeDeviceViewRepository = typeDeviceViewRepository;
    }

    @Transactional
    public ServiceType save(ServiceTypeCommand serviceTypeCommand) {
        controlUnique(null, serviceTypeCommand);
        validateExistenceFK(serviceTypeCommand);
        ServiceType serviceType = serviceTypeDataMapper.serviceTypeCommandToServiceType(serviceTypeCommand);
        serviceType.validate();
        serviceType.initialize();
        ServiceType serviceResult = serviceTypeRepository.save(serviceType);
        if(serviceResult == null) {
            throw new ServiceDomainException("ServiceType is not created");
        }
        return serviceResult;
    }

    public ServiceType update(UUID id, ServiceTypeCommand serviceTypeCommand) {
        controlExistence(id);
        validateExistenceFK(serviceTypeCommand);
        controlUnique(id, serviceTypeCommand);
        ServiceType serviceType = serviceTypeDataMapper.serviceTypeCommandToServiceTypeWithId(id, serviceTypeCommand);
        serviceType.validate();
        ServiceType serviceResult = serviceTypeRepository.save(serviceType);
        if(serviceResult == null) {
            throw new ServiceDomainException("ServiceType is not created");
        }
        return serviceResult;
    }

    public ServiceType delete(UUID id) {
        controlExistence(id);
        ServiceType serviceType = serviceTypeRepository.findById(new ServiceTypeId(id)).get();
        serviceType.setEnabled(false);
        ServiceType serviceResult = serviceTypeRepository.save(serviceType);
        return serviceResult;
    }

    public NinjaPage<ServiceType> getAll(int page, int size) {
        return serviceTypeRepository.findAll(page, size);
    }

    public ServiceType findById(UUID id) {
        controlExistence(id);
        ServiceType serviceType = serviceTypeRepository.findById(new ServiceTypeId(id)).get();
        return serviceType;
    }

    /**
     * Find by id and type device id and service id
     * @param id
     * @param serviceTypeCommand
     */
    private void controlUnique(UUID id, ServiceTypeCommand serviceTypeCommand) {
        Optional<ServiceType> serviceType;
        if(id == null){
            serviceType = serviceTypeRepository.findTopByTypeDeviceIdAndServiceId(
                    new TypeDeviceId(identifierToUuid(serviceTypeCommand.getTypeDeviceId())),
                    new ServiceId(identifierToUuid(serviceTypeCommand.getServiceId())));
        }else{
            serviceType = serviceTypeRepository.findTopByTypeDeviceIdAndServiceIdAndIdNot(
                    new TypeDeviceId(identifierToUuid(serviceTypeCommand.getTypeDeviceId())),
                    new ServiceId(identifierToUuid(serviceTypeCommand.getServiceId())),
                    new ServiceTypeId(id));
        }

        if(serviceType.isPresent()) {
            throw new ServiceDomainException("ServiceType already exists");
        }
    }

    /**
     * Control if the system type exists
     * @param id
     */
    public void controlExistence(UUID id) {
        Optional<ServiceType> serviceType = serviceTypeRepository.findById(new ServiceTypeId(id));
        if(!serviceType.isPresent()) {
            throw new ServiceDomainException("ServiceType with id "+id+" does not exist");
        }
    }


    private void validateExistenceFK(ServiceTypeCommand serviceTypeCommand) {
        //Validate existence of a service
        this.serviceApplicationHelper.controlExistence(identifierToUuid(serviceTypeCommand.getServiceId()));

        //Validate existence of a type device
        Optional<TypeDeviceView> typeDeviceView = typeDeviceViewRepository.
                findByIdAndEnabled(new TypeDeviceId(identifierToUuid(serviceTypeCommand.getTypeDeviceId())), true);

        if(!typeDeviceView.isPresent()) {
            throw new ServiceDomainException("TypeDevice with id "+serviceTypeCommand.getTypeDeviceId()+" does not exist");
        }
    }

}
