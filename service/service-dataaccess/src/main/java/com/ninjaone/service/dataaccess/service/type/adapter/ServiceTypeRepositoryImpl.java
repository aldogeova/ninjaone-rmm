package com.ninjaone.service.dataaccess.service.type.adapter;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.domain.valueobject.ServiceId;
import com.ninjaone.domain.valueobject.ServiceTypeId;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.service.dataaccess.service.type.entity.ServiceTypeEntity;
import com.ninjaone.service.dataaccess.service.type.mapper.ServiceTypeDataAccessMapper;
import com.ninjaone.service.dataaccess.service.type.repository.ServiceTypeJpaRepository;
import com.ninjaone.service.domain.entity.ServiceType;
import com.ninjaone.service.domain.ports.output.repository.ServiceTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ServiceTypeRepositoryImpl implements ServiceTypeRepository {

    public final ServiceTypeJpaRepository serviceTypeJpaRepository;
    public final ServiceTypeDataAccessMapper serviceTypeDataAccessMapper;

    public ServiceTypeRepositoryImpl(ServiceTypeJpaRepository serviceTypeJpaRepository, ServiceTypeDataAccessMapper serviceTypeDataAccessMapper) {
        this.serviceTypeJpaRepository = serviceTypeJpaRepository;
        this.serviceTypeDataAccessMapper = serviceTypeDataAccessMapper;
    }

    @Override
    public ServiceType save(ServiceType serviceType) {
        return serviceTypeDataAccessMapper
                .serviceTypeEntityToServiceType(serviceTypeJpaRepository
                        .save(serviceTypeDataAccessMapper.serviceTypeToServiceTypeEntity(serviceType)));
    }

    @Override
    public Optional<ServiceType> findById(ServiceTypeId serviceTypeId) {
        return serviceTypeJpaRepository.findById(serviceTypeId.getValue())
                .map(serviceTypeDataAccessMapper::serviceTypeEntityToServiceType);
    }

    @Override
    public Optional<ServiceType> findByIdAndTypeDeviceId(ServiceTypeId serviceTypeId, TypeDeviceId typeDeviceId) {
        return serviceTypeJpaRepository.findByIdAndTypeDeviceId(serviceTypeId.getValue(), typeDeviceId.getValue())
                .map(serviceTypeDataAccessMapper::serviceTypeEntityToServiceType);
    }

    @Override
    public List<ServiceType> findByTypeDeviceId(TypeDeviceId typeDeviceId) {
        return serviceTypeJpaRepository.findByTypeDeviceId(typeDeviceId.getValue())
                .stream()
                .map(serviceTypeDataAccessMapper::serviceTypeEntityToServiceType)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceType> findByServiceId(ServiceId serviceId) {
        return serviceTypeJpaRepository.findByServiceId(serviceId.getValue())
                .stream()
                .map(serviceTypeDataAccessMapper::serviceTypeEntityToServiceType)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ServiceType> findTopByTypeDeviceIdAndServiceId(TypeDeviceId typeDeviceId, ServiceId serviceId) {
        return serviceTypeJpaRepository.findTopByTypeDeviceIdAndServiceId(typeDeviceId.getValue(), serviceId.getValue())
                .map(serviceTypeDataAccessMapper::serviceTypeEntityToServiceType);
    }

    @Override
    public Optional<ServiceType> findTopByTypeDeviceIdAndServiceIdAndIdNot(TypeDeviceId typeDeviceId, ServiceId serviceId, ServiceTypeId serviceTypeId) {
        return serviceTypeJpaRepository.findTopByTypeDeviceIdAndServiceIdAndIdNot(typeDeviceId.getValue(), serviceId.getValue(), serviceTypeId.getValue())
                .map(serviceTypeDataAccessMapper::serviceTypeEntityToServiceType);
    }

    @Override
    public NinjaPage<ServiceType> findAll(int page, int size) {
        Page<ServiceTypeEntity> serviceTypeEntityPage = serviceTypeJpaRepository.findAllByEnabledTrue(PageRequest.of(page, size));
        return serviceTypeDataAccessMapper.serviceTypeEntityPageToServiceTypeNinjaPage(serviceTypeEntityPage);
    }
}
