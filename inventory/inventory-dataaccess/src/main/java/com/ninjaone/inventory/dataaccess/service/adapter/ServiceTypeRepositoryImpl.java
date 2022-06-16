package com.ninjaone.inventory.dataaccess.service.adapter;

import com.ninjaone.inventory.dataaccess.service.mapper.ServiceTypeDataAccessMapper;
import com.ninjaone.inventory.dataaccess.service.repository.ServiceTypeJpaRepository;
import com.ninjaone.inventory.domain.entity.ServiceType;
import com.ninjaone.inventory.domain.ports.output.repository.ServiceTypeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ServiceTypeRepositoryImpl implements ServiceTypeRepository {

    public final ServiceTypeJpaRepository serviceTypeJpaRepository;
    public final ServiceTypeDataAccessMapper serviceTypeDataAccessMapper;

    public ServiceTypeRepositoryImpl(ServiceTypeJpaRepository serviceTypeJpaRepository, ServiceTypeDataAccessMapper serviceTypeDataAccessMapper) {
        this.serviceTypeJpaRepository = serviceTypeJpaRepository;
        this.serviceTypeDataAccessMapper = serviceTypeDataAccessMapper;
    }

    @Override
    public Optional<List<ServiceType>> findAllByTypeDeviceId(UUID typeDeviceId) {
        return  serviceTypeJpaRepository.findAllByTypeDeviceId(typeDeviceId).map(serviceTypeDataAccessMapper::serviceTypeEntitiesListToServiceTypeList);
    }

    @Override
    public Optional<ServiceType> findById(UUID serviceTypeId) {
        return serviceTypeJpaRepository.findById(serviceTypeId).map(serviceTypeDataAccessMapper::serviceTypeEntityToServiceType);
    }
}
