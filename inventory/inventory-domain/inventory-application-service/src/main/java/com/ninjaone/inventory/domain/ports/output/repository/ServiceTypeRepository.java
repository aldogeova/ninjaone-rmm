package com.ninjaone.inventory.domain.ports.output.repository;

import com.ninjaone.inventory.domain.entity.ServiceType;
import com.ninjaone.inventory.domain.entity.TypeDevice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceTypeRepository {

    Optional<List<ServiceType>> findAllByTypeDeviceId(UUID typeDeviceId);

    Optional<ServiceType> findById(UUID serviceTypeId);
}
