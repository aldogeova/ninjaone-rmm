package com.ninjaone.service.domain.services.service;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.domain.valueobject.ServiceId;
import com.ninjaone.service.domain.dto.command.service.ServiceCommand;
import com.ninjaone.service.domain.entity.Service;
import com.ninjaone.service.domain.exception.ServiceDomainException;
import com.ninjaone.service.domain.mapper.ServiceDataMapper;
import com.ninjaone.service.domain.ports.output.repository.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.ninjaone.utils.NinjaStringUtils.defaultFormat;
import static com.ninjaone.utils.NinjaStringUtils.stripAccents;

@Slf4j
@Component
public class ServiceApplicationHelper {

    private final ServiceRepository serviceRepository;
    private final ServiceDataMapper serviceDataMapper;

    public ServiceApplicationHelper(ServiceRepository serviceRepository,
                                    ServiceDataMapper serviceDataMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceDataMapper = serviceDataMapper;
    }

    @Transactional
    public Service save(ServiceCommand serviceCommand) {
        controlUnique(null, serviceCommand.getName());
        Service service = serviceDataMapper.serviceCommandToService(serviceCommand);
        service.validate();
        service.initialize();
        Service serviceResult = serviceRepository.save(service);
        if(serviceResult == null) {
            throw new ServiceDomainException("Service is not created");
        }
        return serviceResult;
    }

    public Service update(UUID id, ServiceCommand serviceCommand) {
        controlExistence(id);
        controlUnique(id, serviceCommand.getName());
        Service service = serviceDataMapper.serviceCommandToServiceWithId(id, serviceCommand);
        service.validate();
        Service serviceResult = serviceRepository.save(service);
        if(serviceResult == null) {
            throw new ServiceDomainException("Service is not created");
        }
        return serviceResult;
    }

    public Service delete(UUID id) {
        controlExistence(id);
        Service service = serviceRepository.findById(new ServiceId(id)).get();
        service.setEnabled(false);
        Service serviceResult = serviceRepository.save(service);
        return serviceResult;
    }

    public NinjaPage<Service> getAll(int page, int size) {
        return serviceRepository.findAll(page, size);
    }

    public Service findById(UUID id) {
        controlExistence(id);
        Service service = serviceRepository.findById(new ServiceId(id)).get();
        return service;
    }

    /**
     * Control if the operative system name is unique
     * @param id
     * @param name
     */
    private void controlUnique(UUID id, String name) {
        Optional<Service> service;
        if(id == null){
            service = serviceRepository.findTopByName(stripAccents(defaultFormat(name)));
        }else{
            service = serviceRepository.findTopByNameAndIdNot(stripAccents(defaultFormat(name)), new ServiceId(id));
        }
        if(service.isPresent()) {
            throw new ServiceDomainException("Service already exists");
        }
    }

    /**
     * Control if the operative system exists
     * @param id
     */
    public void controlExistence(UUID id) {
        Optional<Service> service = serviceRepository.findById(new ServiceId(id));
        if(!service.isPresent()) {
            throw new ServiceDomainException("Service with id "+id+" does not exist");
        }
    }


}
