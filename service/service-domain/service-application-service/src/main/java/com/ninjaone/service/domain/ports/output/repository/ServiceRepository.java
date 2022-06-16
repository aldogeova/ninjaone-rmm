package com.ninjaone.service.domain.ports.output.repository;

import com.ninjaone.domain.base.NinjaPage;
import com.ninjaone.domain.valueobject.ServiceId;
import com.ninjaone.service.domain.entity.Service;

import java.util.Optional;

public interface ServiceRepository {

    Service save(Service service);

    Optional<Service> findById(ServiceId serviceId);

    Optional<Service> findTopByName(String name);

    Optional<Service> findTopByNameAndIdNot(String name, ServiceId serviceId);

    NinjaPage<Service> findAll(int page, int size);


}
