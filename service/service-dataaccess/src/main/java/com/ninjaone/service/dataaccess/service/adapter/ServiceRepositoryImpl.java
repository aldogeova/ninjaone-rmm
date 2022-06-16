package com.ninjaone.service.dataaccess.service.adapter;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.domain.valueobject.ServiceId;
import com.ninjaone.service.dataaccess.service.entity.ServiceEntity;
import com.ninjaone.service.dataaccess.service.mapper.ServiceDataAccessMapper;
import com.ninjaone.service.dataaccess.service.repository.ServiceJpaRepository;
import com.ninjaone.service.domain.entity.Service;
import com.ninjaone.service.domain.ports.output.repository.ServiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ServiceRepositoryImpl implements ServiceRepository {

    private final ServiceJpaRepository serviceJpaRepository;
    private final ServiceDataAccessMapper serviceDataAccessMapper;

    public ServiceRepositoryImpl(ServiceJpaRepository serviceJpaRepository, ServiceDataAccessMapper serviceDataAccessMapper) {
        this.serviceJpaRepository = serviceJpaRepository;
        this.serviceDataAccessMapper = serviceDataAccessMapper;
    }

    @Override
    public Service save(Service service) {
        return serviceDataAccessMapper
                .serviceEntityToService(serviceJpaRepository
                        .save(serviceDataAccessMapper.serviceToServiceEntity(service)));
    }

    @Override
    public Optional<Service> findById(ServiceId serviceId) {
        return serviceJpaRepository.findById(serviceId.getValue())
                .map(serviceDataAccessMapper::serviceEntityToService);
    }

    @Override
    public Optional<Service> findTopByName(String name) {
        return serviceJpaRepository.findTopByName(name)
                .map(serviceDataAccessMapper::serviceEntityToService);
    }

    @Override
    public Optional<Service> findTopByNameAndIdNot(String name, ServiceId serviceId) {
        return serviceJpaRepository.findTopByNameAndIdNot(name, serviceId.getValue())
                .map(serviceDataAccessMapper::serviceEntityToService);
    }

    @Override
    public NinjaPage<Service> findAll(int page, int size) {
        Page<ServiceEntity> serviceEntityPage = serviceJpaRepository.findAllByEnabledTrue(PageRequest.of(page, size));
        return serviceDataAccessMapper.serviceEntityPageToServiceNinjaPage(serviceEntityPage);
    }
}
