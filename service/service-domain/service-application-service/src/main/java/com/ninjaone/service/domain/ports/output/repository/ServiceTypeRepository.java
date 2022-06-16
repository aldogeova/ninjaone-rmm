package com.ninjaone.service.domain.ports.output.repository;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.domain.valueobject.ServiceId;
import com.ninjaone.domain.valueobject.ServiceTypeId;
import com.ninjaone.domain.valueobject.TypeDeviceId;
import com.ninjaone.service.domain.entity.ServiceType;

import java.util.List;
import java.util.Optional;

public interface ServiceTypeRepository {

    ServiceType save(ServiceType serviceType);

    Optional<ServiceType> findById(ServiceTypeId serviceTypeId);

    Optional<ServiceType> findByIdAndTypeDeviceId(ServiceTypeId serviceTypeId, TypeDeviceId typeDeviceId);

    List<ServiceType> findByTypeDeviceId(TypeDeviceId typeDeviceId);

    List<ServiceType> findByServiceId(ServiceId serviceId);

    Optional<ServiceType>  findTopByTypeDeviceIdAndServiceId(TypeDeviceId typeDeviceId, ServiceId serviceId);

    Optional<ServiceType> findTopByTypeDeviceIdAndServiceIdAndIdNot(TypeDeviceId typeDeviceId, ServiceId serviceId, ServiceTypeId serviceTypeId);

    NinjaPage<ServiceType> findAll(int page, int size);

}
